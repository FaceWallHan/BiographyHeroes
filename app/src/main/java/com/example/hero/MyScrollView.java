package com.example.hero;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

public class MyScrollView extends ViewGroup {

    private int screenHeight;
    private int lastY,start,end;
    private Scroller scroller;
    public MyScrollView(Context context) {
        super(context);
        inView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inView(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView(context);
    }
    private int checkAlignment() {
        int mEnd = getScrollY();
        boolean isUp = (mEnd - start) > 0;
        int lastPrev = mEnd % screenHeight;
        int lastNext = screenHeight - lastPrev;
        if (isUp) {
            //向上的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }
    private void inView(Context context){
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenHeight=dm.heightPixels;//屏幕有效的高度
        scroller=new Scroller(context);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY=y;
                //记录触摸点
                start=getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()){
                    scroller.abortAnimation();//中止动画
                }
                int dy=lastY-y;
                if (getScrollY()<0){
                    dy=0;
                }
                if (getScrollY()>getHeight()-screenHeight){
                    dy=0;
                }
                scrollBy(0,dy);
                lastY=y;
                break;
            case MotionEvent.ACTION_UP:
                //记录触摸终点
                int scrollY=getScrollY()-start;
                if (scrollY>0){
                    if (scrollY<screenHeight/3){
                        scroller.startScroll(0,getScrollY(),0,-scrollY);
                    }else {
                        scroller.startScroll(0,getScrollY(),0,screenHeight-scrollY);
                    }
                }else {
                    if (-scrollY<screenHeight/3){
                        scroller.startScroll(0,getScrollY(),0,-scrollY);
                    }else {
                        scroller.startScroll(0,getScrollY(),0,-screenHeight-scrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(0,scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //用于放置子View的位置的方法
        int count=getChildCount();
        //设置ViewGroup的高度
        MarginLayoutParams mlp= (MarginLayoutParams) getLayoutParams();
        mlp.height=screenHeight*count;
        setLayoutParams(mlp);
        //整个ViewGroup的高度即childView的个数*childView屏幕的高度
        for (int i = 0; i < count; i++) {
            View childView=getChildAt(i);
            if (childView.getVisibility()!=GONE){
                //设定每个view需要放置的位置
                int top= (int) (i*screenHeight*0.3);
                int bottom= (int) ((i+1)*screenHeight*0.3);
                childView.layout(l,top,r,bottom);
                Log.d("111111111111111", "onLayout: "+top+"..........."+bottom);
                                // left,top,right,bottom
                //更改每个子view的top和bottom让它们能依次排列下来
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count=getChildCount();
        for (int i = 0; i < count; i++) {
            View childView=getChildAt(i);
            //通知子View对自身进行测量
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }
}
