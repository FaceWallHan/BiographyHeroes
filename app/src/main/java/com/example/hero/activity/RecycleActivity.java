package com.example.hero.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.hero.R;
import com.example.hero.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity implements View.OnClickListener,
                                                    AdapterView.OnItemSelectedListener,
                                                    RecyclerAdapter.OnItemClickListener {
    private List<String>data;
    private RecyclerView recycler;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        inView();
    }
    private void inView(){
        Button add_item=findViewById(R.id.add_item);
        add_item.setOnClickListener(this);
        Button del_item=findViewById(R.id.del_item);
        del_item.setOnClickListener(this);
        data=new ArrayList<>();
        recycler=findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        //设置具有固定大小
        recycler.setItemAnimator(new DefaultItemAnimator());
        //设置显示动画
        Spinner recycle_sp = findViewById(R.id.recycle_sp);
        recycle_sp.setOnItemSelectedListener(this);
        for (int i = 0; i < 7; i++) {
            data.add("recycler");
        }
        adapter=new RecyclerAdapter(data);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.del_item:
                delItem();
                break;
            case R.id.add_item:
                addItem();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                recycler.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
                break;
            case 1:
                recycler.setLayoutManager(new GridLayoutManager(RecycleActivity.this,3));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(View view, int position) {
        //设置点击动画
        view.animate().translationZ(10f).setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationCancel(animation);
                        view.animate().translationZ(1f).setDuration(500).start();
                    }
                }).start();
    }
    public void addItem(){
        data.add("recycler");
        int size = data.size();
        if (size>0){
            adapter.notifyDataSetChanged();
        }
    }
    public void delItem(){
        int size = data.size();
        if (size>0){
            data.remove(size-1);
            adapter.notifyDataSetChanged();
        }
    }
}