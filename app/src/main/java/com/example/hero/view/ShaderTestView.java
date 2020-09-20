package com.example.hero.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hero.R;

public class ShaderTestView extends View {
    public ShaderTestView(Context context) {
        super(context);
    }

    public ShaderTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        useLinearShader(canvas);
    }
    private void useLinearShader(Canvas canvas){
        Paint paint=new Paint();
        paint.setShader(new LinearGradient(0,0,400,400, Color.BLUE,Color.YELLOW, Shader.TileMode.REPEAT));
        canvas.drawRect(0,0,400,400,paint);
    }
    private void useBitmapShader(Canvas canvas){
        @SuppressLint("DrawAllocation")
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.default_head);
        @SuppressLint("DrawAllocation")
        BitmapShader shader=new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        @SuppressLint("DrawAllocation")
        Paint paint=new Paint();
        paint.setShader(shader);
        canvas.drawCircle(500,200,200,paint);
    }
}
