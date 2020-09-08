package com.example.hero.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MyViewGroupA extends ViewGroup {
    public static final String TAG = "xys";
    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyViewGroupA dispatchTouchEvent: "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyViewGroupA onInterceptTouchEvent: "+ev.getAction());
        return true;
        //true：拦截，不继续.false:不拦截，继续流程
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyViewGroupA onTouchEvent: "+ev.getAction());
        return super.onTouchEvent(ev);
    }
}
