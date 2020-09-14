package com.example.hero.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class DragView extends View {
    private int lastX,lastY;
    private Scroller scroller;

    public DragView(Context context) {
        super(context);
        inView(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView(context);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        coordinateSystem(event);//坐标系
        //viewSystem(event);//绝对坐标系
        return true;
    }
    private void viewSystem(MotionEvent event){
        int rawX= (int) event.getX();
        int rawY= (int) event.getY();
        //获取触摸点的坐标
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录触摸点坐标
                lastX=rawX;
                lastY=rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX=rawX-lastX;
                int offsetY=rawY-lastY;
                //在当前left，right,top,bottom的基础上加上偏移量
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                //重新设置初始坐标
                lastX=rawX;
                lastY=rawY;
                //每次执行完ACTION_MOVE的逻辑后，一定要重新设置初始坐标，这样才能准确地获取偏移量
                break;
        }
        /**
         * 感觉没有相对坐标系滑动平滑？？？
         * */
    }
    private void coordinateSystem(MotionEvent event){
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //记录触摸点坐标
                lastX=x;
                lastY=y;
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，执行滑动过程
                View viewGroup= (View) getParent();
                scroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY(),500);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX=x-lastX;
                int offsetY=y-lastY;
                //在当前left，right,top,bottom的基础上加上偏移量
                /**
                 * layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                 * */
                /**
                 * offsetLeftAndRight(offsetX);
                 * offsetTopAndBottom(offsetY);
                 * */

                /**
                 * LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) getLayoutParams();
                 * params.leftMargin= getLeft()+offsetX;
                 * params.topMargin= getTop()+offsetY;
                 * setLayoutParams(params);
                 * **/

                 View parent= (View) getParent();
                 parent.scrollBy(-offsetX,-offsetY);


                break;
        }
    }
    private void inView(Context context){
        setBackgroundColor(Color.RED);
        scroller=new Scroller(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断scroller是否执行完毕
        if (scroller.computeScrollOffset()){
            View view= (View) getParent();
                            //获得当前的滑动坐标
            view.scrollTo(scroller.getCurrX(),scroller.getCurrY());
            //通过重绘来不断调用computeScroll
            invalidate();
            //invalidate()--->draw()---->computeScroll()
        }
    }
}
