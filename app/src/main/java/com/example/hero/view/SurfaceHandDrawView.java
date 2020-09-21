package com.example.hero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SurfaceHandDrawView extends SurfaceView implements SurfaceHolder.Callback ,Runnable {
    //SurfaceHolder
    private SurfaceHolder holder;
    //用于绘画的Canvas
    private Canvas canvas;
    //子线程标志位？？？
    private boolean isDrawing;
    private Paint paint;
    private Path path;
    public SurfaceHandDrawView(Context context) {
        super(context);
        inView();
    }

    public SurfaceHandDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public SurfaceHandDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeJoin(Paint.Join.ROUND);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        isDrawing=true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        isDrawing=false;
    }
    private void drawSome(){
        try {
            canvas=holder.lockCanvas();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
        }
        return true;
    }

    @Override
    public void run() {
        long start=System.currentTimeMillis();
        while (isDrawing){
            drawSome();
        }
        long end=System.currentTimeMillis();
        if (end-start<100){
            try {
                Thread.sleep(100-(end-start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
