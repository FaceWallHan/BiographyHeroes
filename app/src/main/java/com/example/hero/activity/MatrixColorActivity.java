package com.example.hero.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class MatrixColorActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_matrix;
    private GridLayout group;
    private Bitmap bitmap;
    private int width,height;
    private EditText[] editTexts=new EditText[20];
    private float[] colors=new float[20];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_color_layout);
        inView();
    }
    private void inView(){
        img_matrix=findViewById(R.id.img_matrix);
        group=findViewById(R.id.group);
        Button change = findViewById(R.id.change);
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);
        change.setOnClickListener(this);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ask);
        group.post(new Runnable() {
            @Override
            public void run() {
                width=group.getWidth()/5;
                height=group.getHeight()/5;
                addEdit();
                initMatrix();
            }
        });
    }
    private void addEdit(){
        for (int i = 0; i < 20; i++) {
            EditText edit=new EditText(MatrixColorActivity.this);
            //edit.setInputType(InputType.TYPE_CLASS_NUMBER);
            editTexts[i]=edit;
            group.addView(edit,width,height);
        }
    }
    private void initMatrix(){
        for (int i = 0; i < editTexts.length; i++) {
            if (i%6==0){
                editTexts[i].setText(String.valueOf(1));
            }else {
                editTexts[i].setText(String.valueOf(0));
            }
        }
    }
    private void setMatrix(){
        for (int i = 0; i < editTexts.length; i++) {
            colors[i]=Float.parseFloat(editTexts[i].getText().toString());
        }
    }
    private void setImageMatrix(){
        Bitmap bmp=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        ColorMatrix matrix=new ColorMatrix();
        matrix.set(colors);
        Canvas canvas=new Canvas(bmp);
        Paint paint=new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        img_matrix.setImageBitmap(bmp);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                setMatrix();
                setImageMatrix();
                break;
            case R.id.reset:
                initMatrix();
                setMatrix();
                setImageMatrix();
                break;
        }
    }
}
