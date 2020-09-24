package com.example.hero.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;
import com.example.hero.animation.CustomAnimation;

public class AnimationTestActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_test_layout);
        LinearLayout layout=findViewById(R.id.layout);
        layoutAnimation(layout);
        final Button anim=findViewById(R.id.anim);
        anim.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ObjectAnimatorBinding")
            @Override
            public void onClick(View view) {
                //propertyAnimations(anim);
                //objectText(anim);
                //setValueAnimation(anim);
                //setXMLAnimation(anim);
                //directAnimation(anim);
                //anim.startAnimation(new CustomAnimation(true));
                CustomAnimation animation=new CustomAnimation(false);
                animation.setRotateY(30);
                anim.startAnimation(animation);
            }
        });

    }
    private void layoutAnimation(LinearLayout layout){
        //设置过渡动画
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(2000);
        //设置布局动画的显示属性
        LayoutAnimationController controller=new LayoutAnimationController(scaleAnimation,0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);//子view的显示顺序
        //为ViewGroup设置布局动画
        layout.setLayoutAnimation(controller);
    }
    private void directAnimation(View view){
        //直接驱动属性动画
        view.animate()
                .alpha(0)
                .y(300)
                .setDuration(300)
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                })
                .start();
    }
    private void setXMLAnimation(View view){
        Animator animator= AnimatorInflater.loadAnimator(AnimationTestActivity.this,R.animator.scalex);
        animator.setTarget(view);
        animator.start();
    }
    private void setValueAnimation(final Button button){
        //不提供任何动画效果，监听数值变化
        ValueAnimator animator= ValueAnimator.ofFloat(0,100);
        animator.setTarget(button);
        animator.setDuration(5000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value= (Float) valueAnimator.getAnimatedValue();
                button.setText(String.valueOf(value));
            }
        });
    }
    private void propertyAnimations(View view){
        PropertyValuesHolder holder1=PropertyValuesHolder.ofFloat("translationX",300f);
        PropertyValuesHolder holder2=PropertyValuesHolder.ofFloat("scaleX",1,0.1f);
        PropertyValuesHolder holder3=PropertyValuesHolder.ofFloat("scaleY",1,0.1f);
        ObjectAnimator.ofPropertyValuesHolder(view,holder1,holder2,holder3)
                        .setDuration(1000)
                        .start();
        //多属性动画的共同作用
    }
    private void setAnimationSet(View view){
        ObjectAnimator holder1=ObjectAnimator.ofFloat(view,"translationX",300f);
        ObjectAnimator holder2=ObjectAnimator.ofFloat(view,"scaleX",1,0.1f);
        ObjectAnimator holder3=ObjectAnimator.ofFloat(view,"scaleY",1,0.1f);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(holder1,holder2,holder3);
        set.start();
        //较PropertyValuesHolder能实现更能精确的顺序控制
    }
    private void objectText(View view){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",300);
        animator.setDuration(300);
        animator.start();
    }
    private static class WrapperView{
        private View view;

        public WrapperView(View view) {
            this.view = view;
        }

        public int getWidth() {
            return view.getLayoutParams().width;
        }

        public void setWidth(int width) {
            view.getLayoutParams().width=width;
            view.requestLayout();
        }

//        WrapperView wrapperView=new WrapperView(anim);
//        ObjectAnimator.ofFloat(wrapperView,"width",500).setDuration(5000).start();
    }
}
