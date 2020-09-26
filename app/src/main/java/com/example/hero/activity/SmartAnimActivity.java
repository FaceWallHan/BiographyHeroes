package com.example.hero.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

import java.util.ArrayList;
import java.util.List;

public class SmartAnimActivity  extends AppCompatActivity implements View.OnClickListener {
    private boolean flag=true;
    private List<ImageView> views;
    private int[] res = {R.id.imageView_a, R.id.imageView_b, R.id.imageView_c,
            R.id.imageView_d, R.id.imageView_e};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_anim_layout);
        views=new ArrayList<>();
        for (int re : res) {
            ImageView imageView = (ImageView) findViewById(re);
            imageView.setOnClickListener(this);
            views.add(imageView);
        }
    }
    private void stopAnim(){
        ObjectAnimator a0=ObjectAnimator.ofFloat(views.get(0),"alpha",0.5f,1f);
        ObjectAnimator a1=ObjectAnimator.ofFloat(views.get(1),"translationY",200f,0f);
        ObjectAnimator a2=ObjectAnimator.ofFloat(views.get(2),"translationX",200f,0f);
        ObjectAnimator a3=ObjectAnimator.ofFloat(views.get(3),"translationY",-200f,0f);
        ObjectAnimator a4=ObjectAnimator.ofFloat(views.get(4),"translationX",-200f,0f);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(a0,a1,a2,a3,a4);
        set.start();
        flag=true;
    }
    private void startAnim(){
        ObjectAnimator a0=ObjectAnimator.ofFloat(views.get(0),"alpha",1f,0.5f);
        ObjectAnimator a1=ObjectAnimator.ofFloat(views.get(1),"translationY",200f);
        ObjectAnimator a2=ObjectAnimator.ofFloat(views.get(2),"translationX",200f);
        ObjectAnimator a3=ObjectAnimator.ofFloat(views.get(3),"translationY",-200f);
        ObjectAnimator a4=ObjectAnimator.ofFloat(views.get(4),"translationX",-200f);
        AnimatorSet set=new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(a0,a1,a2,a3,a4);
        set.start();
        flag=false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageView_a) {
            if (flag) {
                startAnim();
            } else {
                stopAnim();
            }
        } else {
            Toast.makeText(this, view.getId() + "", Toast.LENGTH_SHORT).show();
        }
    }
}
