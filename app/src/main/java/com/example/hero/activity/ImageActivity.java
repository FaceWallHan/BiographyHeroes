package com.example.hero.activity;

import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class ImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);
        TextView tv_rect=findViewById(R.id.tv_rect);
        TextView tv_circle=findViewById(R.id.tv_circle);
        ViewOutlineProvider rect=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为指定形状
                outline.setRoundRect(0,0,view.getHeight(),view.getWidth(),30);
            }
        };
        ViewOutlineProvider circle=new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0,0,view.getWidth(),view.getHeight());
            }
        };
        tv_circle.setOutlineProvider(circle);
        tv_rect.setOutlineProvider(rect);
        tv_rect.setClipToOutline(true);//开启裁剪
        tv_circle.setClipToOutline(true);//开启裁剪
    }
}
