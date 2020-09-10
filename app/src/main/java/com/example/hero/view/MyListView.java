package com.example.hero.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyListView extends ListView {
    private int maxOverDistance=50;
    public MyListView(Context context) {
        super(context);
        inView(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inView(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView(context);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //具有弹性
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverDistance, isTouchEvent);
    }
    private void inView(Context context){
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        float density= metrics.density;//屏幕密度？？？让不同分辨率的弹性距离基本一致
        maxOverDistance= (int) density*maxOverDistance;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
