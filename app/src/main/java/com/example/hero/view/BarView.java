package com.example.hero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;


public class BarView extends View {
    private int rectCount,width,rectWidth,rectHeight;
    private Paint paint;
    public BarView(Context context) {
        super(context);
        inView();
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        paint=new Paint();
        //paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        rectCount=12;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int offset = 5;//每个条形之间的距离
        for (int i = 0; i < rectCount; i++) {
            double random=Math.random();//0~1的小数
            float currentHeight= (float) (rectHeight*random);//小矩形的高
            float left=(float) (width*0.4/2+rectWidth*i+offset);
            float right=(float) (width*0.4/2+rectWidth*(i+1));
            canvas.drawRect(left, currentHeight, right, rectHeight,paint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=getWidth();
        rectHeight=getHeight();
        rectWidth= (int) (width*0.6/rectCount);
        //渐变渲染器
        LinearGradient linearGradient=new LinearGradient(0,0,rectWidth,rectHeight, Color.YELLOW,Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }
}
