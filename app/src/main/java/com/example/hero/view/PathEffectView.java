package com.example.hero.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathEffectView extends View {
    private Paint paint;
    private Path mainPath;
    private PathEffect[] effects;
    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }
    private void inView(){
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);
        mainPath=new Path();
        mainPath.moveTo(0,0);
        for (int i = 0; i <= 30; i++) {
            mainPath.lineTo(i*35, (float) (Math.random()*100));
        }
        effects=new PathEffect[6];
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        effects[0]=null;
        effects[1]=new CornerPathEffect(30);
        effects[2]=new DiscretePathEffect(3,5);
        effects[3]=new DashPathEffect(new float[]{20,10,5,10},0);
        Path path=new Path();
        path.addRect(0,0,8,8,Path.Direction.CCW);
        effects[4]=new PathDashPathEffect(path,12,0,PathDashPathEffect.Style.ROTATE);
        effects[5]=new ComposePathEffect(effects[3],effects[1]);
        for (PathEffect effect : effects) {
            paint.setPathEffect(effect);
            canvas.drawPath(mainPath, paint);
            canvas.translate(0, 200);
        }
    }
    //两个path变量混淆！！！
}
