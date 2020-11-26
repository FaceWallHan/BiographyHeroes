package com.example.hero.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;
import com.example.hero.adapter.AMInfoAdapter;
import com.example.hero.bean.AMProcessInfo;

import java.util.ArrayList;
import java.util.List;

public class AMInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.am_info_layout);
        ListView am_lv=findViewById(R.id.am_lv);
        AMInfoAdapter adapter=new AMInfoAdapter(this,getRunningProcessInfo());
        am_lv.setAdapter(adapter);
    }
    private List<AMProcessInfo> getRunningProcessInfo(){
        ArrayList<AMProcessInfo> infos = new ArrayList<>();
        ActivityManager manager= (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : processes) {
            int pid=appProcessInfo.pid;
            int uid=appProcessInfo.uid;
            String processName=appProcessInfo.processName;
            int[] memoryPid=new int[]{pid};
            Debug.MemoryInfo[] memoryInfo = manager.getProcessMemoryInfo(memoryPid);
            int memorySize=memoryInfo[0].getTotalPss();
            AMProcessInfo amProcessInfo=new AMProcessInfo();
            amProcessInfo.setMemorySize(String.valueOf(memorySize));
            amProcessInfo.setPid(String.valueOf(pid));
            amProcessInfo.setUid(String.valueOf(uid));
            amProcessInfo.setProcessName(String.valueOf(processName));
            infos.add(amProcessInfo);
        }
        return infos;
    }
}
