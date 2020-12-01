package com.example.hero.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

import java.util.Objects;

public class TransitionToActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getIntExtra("flag",0);
        switch (flag){
            case 0:
                //分解
                getWindow().setEnterTransition(new Explode());
                break;
            case 1:
                //滑动
                getWindow().setEnterTransition(new Slide());
                break;
            case 2:
                //淡出
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                break;
        }
        setContentView(R.layout.transition_to_layout);

    }
}
