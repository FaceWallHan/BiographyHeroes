package com.example.hero.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ClickView extends View {

    public ClickView(Context context) {
        super(context);
    }

    public ClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        drawLayer(canvas);

    }
    private void drawCircle(Canvas canvas){
        float height=getMeasuredHeight();
        float width=getMeasuredWidth();
        //画外圆
        @SuppressLint("DrawAllocation")
        Paint paintCircle=new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);//抗锯齿
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(width/2,height/2,width/2,paintCircle);
        //画刻度
        @SuppressLint("DrawAllocation")
        Paint paintDegree=new Paint();
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 12; i++) {
            //区分整点与非整点
            if (i==0||i==3||i==6||i==9){
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);

                canvas.drawLine(width/2,height/2-width/2,width/2,height/2-width/2+60,paintDegree);
                String degree=String.valueOf(i);
                if (i==0){
                    degree="12";
                }
                //文字的宽度？
                canvas.drawText(degree,width/2-paintDegree.measureText(degree)/2,height/2-width/2+90,paintDegree);
                //X开始的位置                                   //基线的位置

            }else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(width/2,height/2-width/2,width/2,height/2-width/2+30,paintDegree);
                String degree=String.valueOf(i);
                canvas.drawText(degree,width/2-paintDegree.measureText(degree)/2,height/2-width/2+60,paintDegree);
            }
            //通过旋转画布简化坐标运算
            canvas.rotate(30,width/2,height/2);
        }
        //画指针
        @SuppressLint("DrawAllocation")
        Paint paintHour=new Paint();
        paintHour.setStrokeWidth(20);
        @SuppressLint("DrawAllocation")
        Paint paintMinute=new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();//保存画布
        canvas.translate(width/2,height/2);//移动原点
        canvas.drawLine(0,0,100,100,paintHour);
        canvas.drawLine(0,0,100,200,paintMinute);
        canvas.restore();//合并图层
    }
    private void drawLayer(Canvas canvas){
        //图层同样是基于栈的结构进行管理的
        Paint paint=new Paint();
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(150,150,100,paint);

        canvas.saveLayerAlpha(0,0,400,400,127);//入栈(创建新图层)
        paint.setColor(Color.RED);
        canvas.drawCircle(200,200,100,paint);
        canvas.restore();//入栈
        //特别注意的是  saveLayerAlpha()restore()要同时使用，才能够在canvas 画出多个层次，就是花多少层就要有多少对两个函数！
    }
}
