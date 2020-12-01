package com.example.hero.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class TransitionFromActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private Button fab,share;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_from_layout);
        inView();
    }
    private void inView(){
        Button explode=findViewById(R.id.explode);
        explode.setOnClickListener(this);
        Button slide=findViewById(R.id.slide);
        slide.setOnClickListener(this);
        Button fade=findViewById(R.id.fade);
        fade.setOnClickListener(this);
        share=findViewById(R.id.share);
        share.setOnClickListener(this);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        intent=new Intent(this,TransitionToActivity.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.explode:
                intent.putExtra("flag",0);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.slide:
                intent.putExtra("flag",1);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.fade:
                intent.putExtra("flag",2);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.share:
                //创建单个共享元素
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,fab,"fab").toBundle());
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this,
                        Pair.create(fab,"fab"),
                        Pair.create(share,"share")
                ).toBundle());
                //创建多个共享元素
                break;
        }
    }
}
