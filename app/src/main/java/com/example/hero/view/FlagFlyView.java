package com.example.hero.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hero.R;

public class FlagFlyView extends View {
    private final  int WIDTH=200,HEIGHT=200;
    private Bitmap bitmap;
    private int COUNT=(WIDTH+1)*(HEIGHT+1);
    private float []orig=new float[COUNT*2];
    private float []verts =new float[COUNT*2];
    private float A,k=1;
    public FlagFlyView(Context context) {
        super(context);
        inView(context);
    }

    public FlagFlyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView(context);
    }

    public FlagFlyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView(context);
    }
    private void inView(Context context){
        setFocusable(true);//设置焦点?
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.ask);
        float bitmapWidth=bitmap.getWidth();
        float bitmapHeight=bitmap.getHeight();
        int index=0;
        for (int y = 0; y <=HEIGHT; y++) {
            float fy=bitmapHeight*y/HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx=bitmapWidth*x/WIDTH;
                orig[index*2]=verts[index*2]=fx;
                orig[index*2+1]=verts[index*2+1]=fy+100;
                index+=1;
            }
        }
        A=50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        flagSave();
        k+=0.1;
        canvas.drawBitmapMesh(bitmap,WIDTH,HEIGHT,verts,0,null,0,null);
        invalidate();
    }

    private void flagSave(){
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH; i++) {
                verts[(j*(WIDTH+1)+i)*2]+=0;
                float offsetY= (float) Math.sin((float)i/WIDTH*2*Math.PI+Math.PI*k);
                verts[(j*(WIDTH+1)+i)*2+1]=orig[(j*WIDTH+i)*2+1]+offsetY*A;
            }
        }
    }
}
