package com.example.hero.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import com.example.hero.R;

import java.util.Objects;

public class PaletteTestActivity extends AppCompatActivity {
    private boolean flag=true;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palette_test_layout);
        Button ev_tv=findViewById(R.id.ev_tv);
        ev_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        ev_tv.animate().translationZ(20);
                        break;
                    case MotionEvent.ACTION_UP:
                        ev_tv.animate().translationZ(0);
                        break;
                }
                return false;
            }
        });
        //创建Palette对象
        Palette.from(getImageBit(findViewById(R.id.palette_iv))).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if (palette!=null){
                    Window window=getWindow();
                    //设置色调
                    window.setStatusBarColor(palette.getDarkMutedColor(Color.RED));
                }
            }
        });
    }
    private Bitmap getImageBit(ImageView view){
        return ((BitmapDrawable)(view.getDrawable())).getBitmap();
    }
}
