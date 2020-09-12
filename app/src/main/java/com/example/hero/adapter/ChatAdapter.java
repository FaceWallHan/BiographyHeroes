package com.example.hero.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hero.ChatBean;
import com.example.hero.R;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter<ChatBean> {
    private List<ChatBean> list;
    private int currentItem;

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public ChatAdapter(@NonNull Context context, List<ChatBean> list) {
        super(context, 0);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public ChatBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChatBean bean=list.get(position);
        TextView text;
        if (bean.isType()){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_in_layout,parent,false);
            text=convertView.findViewById(R.id.text_in);
        }else{
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_out_layout,parent,false);
            text=convertView.findViewById(R.id.text_out);
        }
        String content=bean.getContent();
        if (content.length()>13){
            content=content.substring(0,13);
        }
        LinearLayout layout=new LinearLayout(parent.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        if (position==getCurrentItem()){
            layout.addView(addFocus(parent.getContext()));
        }else {
            layout.addView(addNormalView(parent.getContext(),content));
        }
        text.setText(content);
        return convertView;
    }
    private View addFocus(Context context){
        ImageView view=new ImageView(context);
        view.setBackgroundResource(R.drawable.giao);
        return view;
    }
    private View addNormalView(Context context,String data){
        LinearLayout layout=new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        ImageView img=new ImageView(context);
        img.setBackgroundResource(R.drawable.left);
        layout.addView(img,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView text=new TextView(context);
        text.setText(data);
        img.setBackgroundResource(R.drawable.left);
        layout.addView(text,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setGravity(Gravity.CENTER);
        return layout;
    }
}
