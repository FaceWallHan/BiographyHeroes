package com.example.hero.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class TimeAnimActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_anim_layout);
        final TextView time=findViewById(R.id.time);
        ValueAnimator animator=ValueAnimator.ofFloat(0,10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                String text="$"+valueAnimator.getAnimatedValue();
                time.setText(text);
            }
        });
        animator.setDuration(3000);
        animator.start();
    }
}
