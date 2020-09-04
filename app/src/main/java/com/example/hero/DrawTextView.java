package com.example.hero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class DrawTextView extends TextView {
    private Paint firstPaint,secondPaint;

    public DrawTextView(Context context) {
        super(context);
        inView();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制外层矩形
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),firstPaint);
        //绘制内层矩形
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,secondPaint);
        canvas.save();//用于临时保存画布坐标系统的状态
        //绘制文字前平移10像素
        canvas.translate(10,0);
        /**在回调父类方法前，实现自己的逻辑，对TextView来说即是在绘制文本前*/
        super.onDraw(canvas);
        /**在回调父类方法后，实现自己的逻辑，对TextView来说即是在绘制文本后*/
        canvas.restore();
        /**可以用来恢复save之后设置的状态
         * restore方法前必须有save方法，不然会报Underflow in restore错误
         * */
    }
    private void inView(){
        firstPaint=new Paint();
        firstPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        firstPaint.setStyle(Paint.Style.FILL);
        secondPaint=new Paint();
        secondPaint.setColor(Color.YELLOW);
        secondPaint.setStyle(Paint.Style.FILL);
    }
}
