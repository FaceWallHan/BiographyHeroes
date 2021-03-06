package com.example.hero.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MeasureView extends View {
    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int measureWidth(int measureSpec){
        int result=0;
        int specMode=MeasureSpec.getMode(measureSpec);
        int specSize=MeasureSpec.getSize(measureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            //EXACTLY精确值模式，指定为具体数值或math_parent时使用的是此模式
            result=specSize;
        }else {
            result=200;
            if (specMode==MeasureSpec.AT_MOST){
                //AT_MOST最大值模式，指定为warp_parent，控件大小随着控件的子控件大小或内容的变化而变化
                result=Math.min(result,specSize);
            }
        }
        //UNSPECIFIED不指定其大小测量模式，通常情况下在绘制自定义view时才会使用
        return  result;
    }
    private int measureHeight(int measureSpec){
        return measureWidth(measureSpec);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
        @SuppressLint("DrawAllocation")
        Bitmap bitmap1=Bitmap.createBitmap(20,20, Bitmap.Config.ARGB_8888);
        @SuppressLint("DrawAllocation")
        Bitmap bitmap2=Bitmap.createBitmap(20,20, Bitmap.Config.ARGB_8888);
        canvas.drawBitmap(bitmap1,0,0,null);
        canvas.drawBitmap(bitmap2,0,0,null);
        @SuppressLint("DrawAllocation")
        Canvas canvas1=new Canvas(bitmap1);
         */
//        canvas.draw
        super.onDraw(canvas);
    }
}
