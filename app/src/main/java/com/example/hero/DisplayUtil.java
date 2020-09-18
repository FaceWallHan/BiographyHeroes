package com.example.hero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
    public static Bitmap handleImageNegative(Bitmap bitmap){
        //底片效果
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);//(由4个8位组成即32位)
        int []oldPx=new int[width*height];
        int []newPx=new int[width*height];
        bitmap.getPixels(oldPx,0,width,0,0,width,height);
        for (int i = 0; i <width*height ; i++) {
            color=oldPx[i];
            r= Color.red(color);
            g= Color.green(color);
            b= Color.blue(color);
            a= Color.alpha(color);

            r=255-r;
            g=255-g;
            b=255-b;

            if (r>255){
                r=255;
            }else if (r<0){
                r=0;
            }
            if (g>255){
                g=255;
            }else if (g<0){
                g=0;
            }
            if (b>255){
                b=255;
            }else if (b<0){
                b=0;
            }
            newPx[i]=Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,width,0,0,width,height);
        return bmp;
    }
    public  static Bitmap handleImageOldPhoto(Bitmap bitmap){
        //老照片
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bmp=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);//(由4个8位组成即32位)
        int []oldPx=new int[width*height];
        int []newPx=new int[width*height];
        bitmap.getPixels(oldPx,0,width,0,0,width,height);
        for (int i = 0; i <width*height ; i++) {
            color=oldPx[i];
            r= Color.red(color);
            g= Color.green(color);
            b= Color.blue(color);
            a= Color.alpha(color);

            r= (int) (0.393*r+0.769*g+0.189*b);
            g= (int) (0.349*r+0.686*g+0.168*b);
            b= (int) (0.272*r+0.534*g+0.131*b);
            if (r>255){
                r=255;
            }
            if (g>255){
                g=255;
            }
            if (b>255){
                b=255;
            }
            newPx[i]=Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,width,0,0,width,height);
        return bmp;

    }
    public  static Bitmap handleImageRelief(Bitmap bitmap){
        //浮雕
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int color;
        int colorBefore=0;
        int r,g,b,a;
        int r1,g1,b1;
        Bitmap bmp=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);//(由4个8位组成即32位)
        int []oldPx=new int[width*height];
        int []newPx=new int[width*height];
        bitmap.getPixels(oldPx,0,width,0,0,width,height);
        for (int i = 1; i <width*height ; i++) {
            colorBefore=oldPx[i-1];

            r= Color.red(colorBefore);
            g= Color.green(colorBefore);
            b= Color.blue(colorBefore);
            a= Color.alpha(colorBefore);

            color=oldPx[i];
            r1=Color.red(color);
            g1=Color.green(color);
            b1=Color.blue(color);

            r=(r-r1+127);
            g=(g-g1+127);
            b=(b-b1+127);
            if (r>255){
                r=255;
            }
            if (g>255){
                g=255;
            }
            if (b>255){
                b=255;
            }
            newPx[i]=Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,width,0,0,width,height);
        return bmp;

    }
}
