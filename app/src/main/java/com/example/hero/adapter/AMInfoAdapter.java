package com.example.hero.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hero.R;
import com.example.hero.bean.AMProcessInfo;

import java.util.List;

public class AMInfoAdapter extends ArrayAdapter<AMProcessInfo> {
    private List<AMProcessInfo>processInfos;
    private LayoutInflater inflater;
    public AMInfoAdapter(@NonNull Context context, List<AMProcessInfo>processInfos) {
        super(context, 0);
        this.processInfos=processInfos;
        inflater=LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public AMProcessInfo getItem(int position) {
        return processInfos.get(position);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=inflater.inflate(R.layout.am_info_item_layout,parent,false);
        TextView pid=convertView.findViewById(R.id.pid);
        TextView uid=convertView.findViewById(R.id.uid);
        TextView memorySize=convertView.findViewById(R.id.memorySize);
        TextView processName=convertView.findViewById(R.id.processName);
        AMProcessInfo item = getItem(position);
        assert item != null;
        pid.setText(String.valueOf("Pid:"+item.getPid()));
        uid.setText(String.valueOf("uid:"+item.getUid()));
        memorySize.setText(String.valueOf(item.getMemorySize()+"KB"));
        processName.setText(item.getProcessName());
        return convertView;
    }

    @Override
    public int getCount() {
        return processInfos.size();
    }
}
