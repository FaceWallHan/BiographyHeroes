package com.example.hero.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

public class DrawGroup extends FrameLayout {
    private ViewDragHelper helper;
    private View mainView,menuView;

    public DrawGroup(@NonNull Context context) {
        super(context);
        inView();

    }

    public DrawGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public DrawGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        helper=ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                //如果当前触摸的child是mainView时开始检测
                return child==mainView;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                //水平方向上的滑动
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                //垂直方向上的滑动
                return 0;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                //拖动结束后调用
                super.onViewReleased(releasedChild, xvel, yvel);
                //手指抬起后缓慢移动到指定位置
                if (mainView.getLeft()<300){
                    //关闭菜单
                    helper.smoothSlideViewTo(mainView,0,0);
                    //相当于scroller的startScroll方法
                }else {
                    //打开菜单
                    helper.smoothSlideViewTo(mainView,300,0);
                }
                ViewCompat.postInvalidateOnAnimation(DrawGroup.this);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return helper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper，此操作必不可少
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (helper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView=getChildAt(0);
        mainView=getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
