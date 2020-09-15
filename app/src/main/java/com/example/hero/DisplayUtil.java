package com.example.hero;

import android.content.Context;
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
}
