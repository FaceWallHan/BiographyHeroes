package com.example.hero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.TypedValue;

public class DisplayUtil {
    /**
     * 将px值转换为dip或dp值，保证尺寸不变
     * */
    public static int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }
    /**
     *将dip或dp值转换为px值，保证尺寸不变
     * Density:密度
     * */
    public static int dp2px(Context context,float dipValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dipValue*scale+0.5f);
    }
    /**
     *将px值转换为sp值，保证文字不变
     * scaledDensity：缩放密度
     * */
    public static int px2sp(Context context,float pxValue){
        float fontSize=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontSize+0.5f);
    }
    /**
     *将sp值转换为px值，保证文字不变
     * */
    public static int sp2px(Context context,float pxValue){
        float fontSize=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue*fontSize+0.5f);
    }
    /**
     * dp2px
    * */
    protected int dp2px(int dp,Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
    /**
     * sp2px
     * */
    protected int sp2px(int sp,Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
    public static Bitmap handleImageEffect(Bitmap bitmap,float hue,float lum,float saturation){
        Bitmap bit=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bit);
        Paint paint=new Paint();

        ColorMatrix hueMatrix=new ColorMatrix();//色调
        hueMatrix.setRotate(0,hue);//Red
        hueMatrix.setRotate(1,hue);//Green
        hueMatrix.setRotate(2,hue);//Blue

        ColorMatrix saturationMatrix=new ColorMatrix();//饱和度
        saturationMatrix.setSaturation(saturation);//0时变为灰度图像

        ColorMatrix lumMatrix=new ColorMatrix();//亮度
        lumMatrix.setScale(lum,lum,lum,1);

        ColorMatrix imageMatrix=new ColorMatrix();
        //将矩阵的作用效果混合，叠加处理效果
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(lumMatrix);
        imageMatrix.postConcat(saturationMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));//将颜色矩阵作用到原图
        canvas.drawBitmap(bitmap,0,0,paint);
        //Android系统不允许直接修改原图，必须通过副本的形式来修改图像
        return bit;
    }
}
