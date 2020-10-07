package com.example.hero.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class ProvinceBean {
    private int fillColor= Color.RED,strokeColor=Color.BLACK;
    private Path path;
    public void draw(Canvas canvas, Paint paint){
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(strokeColor);
        canvas.drawPath(path,paint);
        //
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
}
