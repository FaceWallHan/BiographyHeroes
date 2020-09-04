package com.example.hero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class ShineTextView extends TextView {
    private LinearGradient linearGradient;
    private Matrix gradientMatrix;
    private int translate=0,viewWidth=0;
    private Paint paint;
    public ShineTextView(Context context) {
        super(context);
    }

    public ShineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /**组件改变大小时回调*/
        super.onSizeChanged(w, h, oldw, oldh);
        if (viewWidth==0){
            viewWidth=getMeasuredWidth();
            if (viewWidth>0){
                paint=getPaint();//获取当前绘制TextView的Paint
                //渐变渲染器
                linearGradient=new LinearGradient(0,0,viewWidth,0,new int[]{Color.BLUE,0xfffff000,Color.GREEN}, null, Shader.TileMode.REPEAT);
                /**
                 * CLAMP表示，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间。
                 * REPEAT表示，当我们绘制的图形尺寸大于Bitmap尺寸时，会用Bitmap重复平铺整个绘制的区域。
                 * MIRROR与REPEAT类似，当绘制的图形尺寸大于Bitmap尺寸时，MIRROR也会用Bitmap重复平铺整个绘图区域，与REPEAT不同的是，两个相邻的Bitmap互为镜像。
                 * */
                paint.setShader(linearGradient);
                gradientMatrix=new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gradientMatrix!=null){
            translate+=viewWidth/5;
            if (translate>viewWidth*2){
                translate=-viewWidth;
            }
            //遮罩既视感？总距离=正+负
            gradientMatrix.setTranslate(translate,0);//平移
            linearGradient.setLocalMatrix(gradientMatrix);
            postInvalidateDelayed(1000);
        }
    }
}
