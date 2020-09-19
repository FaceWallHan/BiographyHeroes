package com.example.hero.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.DisplayUtil;
import com.example.hero.R;

public class DrawTestActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekHue,seekSaturation,seekLum;
    private float hue,saturation,lum;
    private ImageView img_color;
    private static final  int MAX_VALUE=255,MID_VALUE=127;
    private Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_test_layout);
        inView();
        setSeekBar(seekHue);
        setSeekBar(seekSaturation);
        setSeekBar(seekLum);
        //img_color.setImageBitmap(DisplayUtil.handleImageRelief(bitmap));
    }
    private void inView(){
        seekHue=findViewById(R.id.hue);
        seekSaturation=findViewById(R.id.saturation);
        seekLum=findViewById(R.id.lum);
        img_color=findViewById(R.id.img_color);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ask);
    }
    private void setSeekBar(SeekBar seekBar){
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(MAX_VALUE);
        seekBar.setProgress(MID_VALUE);

    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.hue:
                hue=(progress-MID_VALUE)*1.0F/MID_VALUE*180;
                break;
            case R.id.saturation:
                saturation=progress*1.0F/MID_VALUE;
                break;
            case R.id.lum:
                lum=progress*1.0F/MID_VALUE;
                break;
        }
         img_color.setImageBitmap(DisplayUtil.handleImageEffect(bitmap,hue,lum,saturation));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
