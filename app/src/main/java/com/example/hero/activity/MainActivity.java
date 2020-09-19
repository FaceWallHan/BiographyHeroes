package com.example.hero.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hero.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fillet_view_layout);
        //setContentView(R.layout.flag_view_layout);
        //setContentView(R.layout.group_test_layout);
        //setContentView(R.layout.scrollview_layout);
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