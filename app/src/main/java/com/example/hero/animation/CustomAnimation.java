package com.example.hero.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

public class CustomAnimation extends Animation {
    private int centerWidth,centerHeight;
    private boolean flag;
    private Camera camera;
    private float rotateY=0.0f;

    public void setRotateY(float rotateY) {
        this.rotateY = rotateY;
    }

    public CustomAnimation(boolean flag) {
        this.flag = flag;
        camera=new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
                                    //插值器的时间因子(0~1.0)
        super.applyTransformation(interpolatedTime, t);
        if (flag){
            setTv(interpolatedTime,t);
        }else {
            setCamera(interpolatedTime,t);
        }

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        int duration;
        if (flag){
            duration=1000;
        }else {
            duration=2000;
        }
        setDuration(duration);
        setFillAfter(true);//动画结束后保留状态
        setInterpolator(new BounceInterpolator());//设置默认插值器
        centerHeight=height/2;
        centerWidth=width/2;
    }

    public void setCamera(float interpolatedTime, Transformation t) {
        Matrix matrix=t.getMatrix();
        camera.save();//保存画布
        //使用camera设置旋转的角度
        camera.rotateY(rotateY*interpolatedTime);
        //将旋转变换作用到matrix上
        camera.getMatrix(matrix);
        camera.restore();//合并图层
        //通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(centerWidth,centerHeight);
        matrix.postTranslate(-centerWidth,-centerHeight);
    }

    private void setTv(float interpolatedTime, Transformation t){
        Matrix matrix=t.getMatrix();
        //缩放的中心点
        matrix.preScale(1,1-interpolatedTime,centerWidth,centerHeight);
    }
}
