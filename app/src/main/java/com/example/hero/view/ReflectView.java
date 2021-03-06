package com.example.hero.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hero.R;

public class ReflectView extends View {
    private Paint paint;
    private Bitmap srcBitmap,refBitmap;
    private PorterDuffXfermode xfermode;
    public ReflectView(Context context) {
        super(context);
        inView();
    }

    public ReflectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public ReflectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        srcBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ask);
        Matrix matrix=new Matrix();
        matrix.setScale(1,-1);//垂直翻转
        refBitmap=Bitmap.createBitmap(srcBitmap,0,0,srcBitmap.getWidth(),srcBitmap.getHeight(),matrix,true);
        paint=new Paint();
        paint.setShader(new LinearGradient(0,srcBitmap.getHeight(),0,
                srcBitmap.getHeight()+(float)srcBitmap.getHeight()/4,
                0XDD000000,0X10000000,  Shader.TileMode.CLAMP));
        xfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(srcBitmap,0,0,null);
        canvas.drawBitmap(refBitmap,0,srcBitmap.getHeight(),null);
        paint.setXfermode(xfermode);
        //绘制渐变矩形
        canvas.drawRect(0,srcBitmap.getHeight(),refBitmap.getWidth(),srcBitmap.getHeight()*2,paint);
        paint.setXfermode(null);
    }
}
