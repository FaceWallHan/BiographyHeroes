package com.example.hero.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class CircleRevealActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView rect,oval;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_reveal_layout);
        inView();
    }
    private void inView(){
        rect=findViewById(R.id.rect);
        rect.setOnClickListener(this);
        oval=findViewById(R.id.oval);
        oval.setOnClickListener(this);
        //centerX动画开始的中心点X
        //centerY 动画开始的中心点Y
        //startRadius动画开始半径
        //endRadius动画结束半径
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.oval:
                Animator ovalAnimator= ViewAnimationUtils.createCircularReveal(oval,
                        oval.getWidth()/2,oval.getHeight()/2,oval.getWidth(),0);
                ovalAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                ovalAnimator.setDuration(2000);
                ovalAnimator.start();
                break;
            case R.id.rect:
                //√（100²+100²）~=141.421
                Animator rectAnimator= ViewAnimationUtils.createCircularReveal(rect,
                        0,0,0, (float) Math.hypot(rect.getWidth(),rect.getHeight()));
                //x和y平方和的二次方根
                //Math.hypot(x, y);               //√（x²+y²）
                rectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                //插值器
                rectAnimator.setDuration(2000);
                rectAnimator.start();
                break;
        }
    }
}
