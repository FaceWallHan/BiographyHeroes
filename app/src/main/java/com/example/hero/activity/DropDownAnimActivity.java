package com.example.hero.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class DropDownAnimActivity extends AppCompatActivity {
    private LinearLayout small_drop;
    private int viewMeasureHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drop_down_anim_layout);
        small_drop=findViewById(R.id.small_drop);
        LinearLayout big_drop = findViewById(R.id.big_drop);
        float density = getResources().getDisplayMetrics().density;
        viewMeasureHeight= (int) (density *40+0.5);
        big_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (small_drop.getVisibility()==View.GONE){
                    animOpen(small_drop);
                }else {
                    animClose(small_drop);
                }
            }
        });
    }
    private void animClose(final View view){
        int origHeight=view.getHeight();
        ValueAnimator animator=createDropAnimator(view,origHeight,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }
    private ValueAnimator createDropAnimator(final View view, int start, int end){
        ValueAnimator animator=ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value= (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams params=view.getLayoutParams();
                params.height=value;
                view.setLayoutParams(params);
            }
        });
        return animator;
    }
    private void animOpen(final View view){
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator=createDropAnimator(view,0,viewMeasureHeight);
        animator.start();
    }
}
