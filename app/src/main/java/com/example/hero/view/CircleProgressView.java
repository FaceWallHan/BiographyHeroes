package com.example.hero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleProgressView extends View {
    private int measureWidth,measureHeight;
    private String showText;
    private float circleXY,radius,sweepAngle,sweepValue=66,showTextSize;
    private Paint circlePaint,arcPaint,textPaint;
    private RectF arcRectF;
    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(circleXY,circleXY,radius,circlePaint);
                        //圆心的X坐标，圆心的Y坐标，半径,画笔对象的属性
        //绘制弧线
        canvas.drawArc(arcRectF,270,sweepAngle,false,arcPaint);
                    //RectF oval:圆弧的形状和大小的范围,
                    //float startAngle:圆弧是从哪个角度来顺时针绘画的
                    //float sweepAngle:设置圆弧扫过的角度
                    //boolean useCenter:设置圆弧在绘画的时候，是否经过圆形
                    //Paint paint 画笔对象的属性
        //绘制文字
        canvas.drawText(showText,0,showText.length(),circleXY,circleXY+(showTextSize/4),textPaint);
        //float y为baseline(基线)
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureHeight=MeasureSpec.getSize(heightMeasureSpec);
        measureWidth=MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(measureWidth,measureHeight);
        inView();
    }
    private void inView(){
        float length=0;
        length = Math.min(measureWidth, measureHeight);
        circleXY=length/2;
        radius= (float) (length*0.5/2);//半径是length的1/4
        circlePaint=new Paint();
        circlePaint.setAntiAlias(true);//抗锯齿
        circlePaint.setColor(getResources().getColor(android.R.color.holo_blue_light));

        arcRectF=new RectF((float) (length*0.1),(float) (length*0.1),(float) (length*0.9),(float) (length*0.9));
        //外接矩形，画图吧还是
        sweepAngle=(sweepValue/100f)*360f;
        arcPaint=new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(getResources().getColor(android.R.color.holo_blue_light));
        arcPaint.setStrokeWidth((float) (length*0.1));
        arcPaint.setStyle(Paint.Style.STROKE);
        /**
         * STROKE:只绘制图形轮廓（描边）
         * FILL 只绘制图形内容
         * FILL_AND_STROKE 既绘制轮廓也绘制内容
         * */
        showText=setShowText();
        showTextSize=setShowTextSize();
        textPaint=new Paint();
        textPaint.setTextSize(showTextSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }
    private String setShowText(){
        this.invalidate();
        return "Android Skill";
    }
    private float setShowTextSize(){
        this.invalidate();
        return 50;
    }
    public void foreInvalidate(){
        this.invalidate();
    }

    public void setSweepValue(float mSweepValue) {
        if (mSweepValue!=0){
            sweepValue=mSweepValue;
        }else {
            sweepValue=25;
        }
        this.invalidate();
    }
}
