package com.example.hero.activity;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;
import com.example.hero.adapter.ManagerInfoAdapter;
import com.example.hero.bean.PmAppInfo;

import java.util.ArrayList;
import java.util.List;

public class PMInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int ALL_APP = 0;
    public static final int SYSTEM_APP = 1;
    public static final int THIRD_APP = 2;
    public static final int SDCARD_APP = 3;
    private PackageManager manager;
    private ListView pm_lv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm_info_layout);
        inView();
    }
    private void inView(){
        Button third_app=findViewById(R.id.third_app);
        third_app.setOnClickListener(this);
        Button system_app=findViewById(R.id.system_app);
        system_app.setOnClickListener(this);
        Button all_app=findViewById(R.id.all_app);
        all_app.setOnClickListener(this);
        Button sdcard_app=findViewById(R.id.sdcard_app);
        sdcard_app.setOnClickListener(this);
        pm_lv=findViewById(R.id.pm_lv);
        setData(ALL_APP);
    }
    private List<PmAppInfo> getAppInfo(int flag){
        manager=getPackageManager();
        List<ApplicationInfo> applicationInfoList = manager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<PmAppInfo>appInfos=new ArrayList<>();
        switch (flag){
            case ALL_APP:
                appInfos.clear();
                for (ApplicationInfo info : applicationInfoList) {
                    appInfos.add(makeAppInfo(info));
                }
                break;
            case SYSTEM_APP:
                appInfos.clear();
                for (ApplicationInfo info : applicationInfoList) {
                    if ((info.flags&ApplicationInfo.FLAG_SYSTEM)!=0){
                        appInfos.add(makeAppInfo(info));
                    }
                }
                break;
            case THIRD_APP:
                appInfos.clear();
                for (ApplicationInfo info : applicationInfoList) {
                    if ((info.flags&ApplicationInfo.FLAG_SYSTEM)<=0){
                        appInfos.add(makeAppInfo(info));
                    }if ((info.flags&ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)!=0){
                        appInfos.add(makeAppInfo(info));
                    }
                }
                break;
            case SDCARD_APP:
                appInfos.clear();
                for (ApplicationInfo info : applicationInfoList) {
                    if ((info.flags&ApplicationInfo.FLAG_EXTERNAL_STORAGE)!=0){
                        appInfos.add(makeAppInfo(info));
                    }
                }
                break;
            default:
                return null;

        }
        return appInfos;
    }
    private PmAppInfo makeAppInfo(ApplicationInfo info){
        PmAppInfo appInfo=new PmAppInfo();
        appInfo.setAppIcon(info.loadIcon(manager));
        appInfo.setAppLabel((String) info.loadLabel(manager));
        appInfo.setPackageName(info.packageName);
        return appInfo;
    }
    private void setData(int flag){
        ManagerInfoAdapter adapter=new ManagerInfoAdapter(this,getAppInfo(flag));
        pm_lv.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.third_app:
                setData(THIRD_APP);
                break;
            case R.id.system_app:
                setData(SYSTEM_APP);
                break;
            case R.id.all_app:
                setData(ALL_APP);
                break;
            case R.id.sdcard_app:
                setData(SDCARD_APP);
                break;
        }
    }
}
