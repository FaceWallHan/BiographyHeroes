package com.example.hero.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hero.R;

public class EraseView extends View {
    private Bitmap bgBitmap,fgBitmap;
    private Paint paint;
    private Path path;
    private Canvas canvas;
    public EraseView(Context context) {
        super(context);
        inView();
    }

    public EraseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public EraseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        paint=new Paint();
        paint.setAlpha(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);//拐角风格
        paint.setStrokeWidth(50);
        paint.setStrokeCap(Paint.Cap.ROUND);//线帽
        path=new Path();
        bgBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ask);
        fgBitmap=Bitmap.createBitmap(bgBitmap.getWidth(),bgBitmap.getHeight(),Bitmap.Config.ARGB_8888);
        canvas=new Canvas(fgBitmap);
        canvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.reset();
                path.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;
        }
        canvas.drawPath(path,paint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgBitmap,0,0,null);
        canvas.drawBitmap(fgBitmap,0,0,null);
    }
}
