package com.example.hero.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hero.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surface_hand_draw_layout);
        //setContentView(R.layout.surface_template_layout);
        //setContentView(R.layout.path_effect_view);
        //setContentView(R.layout.reflect_layout);
        //setContentView(R.layout.circle_layout);
        //setContentView(R.layout.erase_layout);
        //setContentView(R.layout.fillet_view_layout);
        //setContentView(R.layout.flag_view_layout);
        //setContentView(R.layout.group_test_layout);
        //setContentView(R.layout.scrollview_layout);
        /****/
//        setContentView(R.layout.activity_main);
//        TopBar topBar=findViewById(R.id.topBar);
//        topBar.setTopBarClickListener(new TopBar.topBarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this, "leftClick", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(MainActivity.this, "rightClick", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    
}