package com.example.hero.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;
import com.example.hero.adapter.ViewHolderAdapter;
import com.example.hero.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class HideTitleActivity extends AppCompatActivity implements View.OnTouchListener {
    private int touchSlop=0;//最低滑动距离，超过这个距离的移动就将其定义为滑动状态
    private MyListView my_list;
    private float firstY,currentY;
    private boolean show=true;
    private ObjectAnimator animator;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hide_title_layout);
        inView();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_MOVE:
                currentY=motionEvent.getY();
                if (currentY-firstY>touchSlop&&show){
                    toolBarAnim(0);
                    show=!show;
                }else  if (firstY-currentY>touchSlop&&!show){
                    toolBarAnim(1);
                    show=!show;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                firstY=motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
    @SuppressLint("ClickableViewAccessibility")
    private void inView(){
        my_list=findViewById(R.id.my_list);
        my_list.setOnTouchListener(this);
        toolbar=findViewById(R.id.toolbar);
//        View header=new View(this);
//        header.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getBarSize()));
//        my_list.addHeaderView(header);
        touchSlop= ViewConfiguration.get(this).getScaledTouchSlop();
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i+"");
        }
        my_list.setAdapter(new ViewHolderAdapter(list,HideTitleActivity.this));
    }
    private int getBarSize(){
        int []attributes={android.R.attr.actionBarSize};
        @SuppressLint("Recycle")
        TypedArray typedArray=this.obtainStyledAttributes(attributes);
        int barSize=(int) typedArray.getDimension(0,0);
        typedArray.recycle();
        return barSize;
    }
    private void toolBarAnim(int flag){
        if (animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        if (flag==0){
            animator=ObjectAnimator.ofFloat(toolbar,"translationY",toolbar.getTranslationY(),0);
        }else {
            animator=ObjectAnimator.ofFloat(toolbar,"translationY",toolbar.getTranslationY(),-toolbar.getHeight());
        }
        animator.start();
    }
    /**
     * 重复造轮子者斩！！！
     *https://github.com/FaceWallHan/SummerVacation/blob/master/app/src/main/java/com/start/head/activity/MaterialDesignActivity.java
     * */
}
