package com.example.hero.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hero.R;
import com.example.hero.bean.PmAppInfo;

import java.util.List;

public class ManagerInfoAdapter extends ArrayAdapter<PmAppInfo> {
    private List<PmAppInfo>appInfos;
    private LayoutInflater inflater;
    public ManagerInfoAdapter(@NonNull Context context, List<PmAppInfo>appInfos) {
        super(context, 0);
        this.appInfos=appInfos;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appInfos.size();
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=inflater.inflate(R.layout.pm_info_item_layout,parent,false);
        ImageView app_icon=convertView.findViewById(R.id.app_icon);
        TextView app_name=convertView.findViewById(R.id.app_name);
        TextView package_name=convertView.findViewById(R.id.package_name);
        PmAppInfo item = getItem(position);
        assert item != null;
        app_icon.setImageDrawable(item.getAppIcon());
        app_name.setText(item.getAppLabel());
        package_name.setText(item.getPackageName());
        return convertView;
    }

    @Nullable
    @Override
    public PmAppInfo getItem(int position) {
        return appInfos.get(position);
    }
}
