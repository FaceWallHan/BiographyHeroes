package com.example.hero.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

public class ProvinceBean {


    private int fillColor= Color.RED,strokeColor=Color.BLACK;
    private Path path;
    public void draw(Canvas canvas, Paint paint,boolean select){
        if (select){
            paint.setStrokeWidth(4);
            paint.setStyle(Paint.Style.STROKE);
            paint.setShadowLayer(6,0,0,Color.RED);
            paint.setColor(Color.WHITE);
            canvas.drawPath(path,paint);
            //
            paint.setStrokeWidth(4);
        }else {
            paint.clearShadowLayer();
            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);
            canvas.drawPath(path,paint);
            //
            paint.setStrokeWidth(2);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fillColor);
        canvas.drawPath(path,paint);
    }
    public ProvinceBean(Path path) {
        this.path = path;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    public boolean isTouch(float x, float y){
        RectF rectF = new RectF();
        path.computeBounds(rectF,true);
        Region region = new Region((int) rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom);
        ////手指触碰的地方为当前区域
        return region.contains((int) x, (int) y);

    }
}
