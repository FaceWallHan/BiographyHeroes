package com.example.hero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback ,Runnable{
    //SurfaceHolder
    private SurfaceHolder holder;
    //用于绘画的Canvas
    private Canvas canvas;
    //子线程标志位？？？
    private boolean isDrawing;
    private Paint paint;
    private Path path;
    private int x,y;
    public SurfaceViewTemplate(Context context) {
        super(context);
        inView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        holder=getHolder();
        holder.addCallback(this);
        setFocusable(false);//焦点
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        path=new Path();
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        isDrawing=true;
        path.moveTo(0,400);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        isDrawing=false;
    }

    @Override
    public void run() {
        while (isDrawing){
            drawSome();
            x+=1;
            y= (int) (100*Math.sin(x*2*Math.PI/180)+400);
            path.lineTo(x,y);
        }
    }
    private void drawSome(){
        try {
            canvas=holder.lockCanvas();
            //draw something...
            canvas.drawColor(Color.WHITE);
            canvas.drawPath(path,paint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas!=null){
                holder.unlockCanvasAndPost(canvas);
            }
        }

    }
}
