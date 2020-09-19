package com.example.hero.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.hero.R;

public class FilletView extends View {
    private Bitmap bitmap,out;
    private Paint paint;
    public FilletView(Context context) {
        super(context);
        inView();
    }

    public FilletView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public FilletView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    private void inView(){
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ask);
        out=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(out);
        paint=new Paint();
        paint.setAntiAlias(true);
        canvas.drawRoundRect(0,0,bitmap.getWidth(),bitmap.getHeight(),80,80,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawBitmap(out,0,0,null);

    }
}
