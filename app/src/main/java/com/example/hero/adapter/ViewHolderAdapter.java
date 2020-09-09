package com.example.hero.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hero.R;

import java.util.List;

public class ViewHolderAdapter extends BaseAdapter {
    private List<String> list;
    private LayoutInflater inflater;

    public ViewHolderAdapter(List<String> list, Context context) {
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    //simpleAdapter？？？
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.view_holder_item_layout,viewGroup,false);
            holder.img=view.findViewById(R.id.holder_image);
            holder.title=view.findViewById(R.id.holder_text);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.img.setBackgroundResource(R.drawable.right);
        holder.title.setText(list.get(i));
        return view;
    }
    private  static class ViewHolder{
        TextView title;
        ImageView img;
        //充分利用listView的视图缓存机制，提高效率
    }
}
