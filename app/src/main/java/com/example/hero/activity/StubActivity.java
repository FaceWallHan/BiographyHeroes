package com.example.hero.activity;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class StubActivity extends AppCompatActivity implements View.OnClickListener {
    private  ViewStub not_often_use;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stub_layout);
        inView();
        Debug.startMethodTracing();
    }
    private void inView(){
        Button visible_bt=findViewById(R.id.visible_bt);
        visible_bt.setOnClickListener(this);
        Button inflate_bt=findViewById(R.id.inflate_bt);
        inflate_bt.setOnClickListener(this);
        Button gone_bt=findViewById(R.id.gone_bt);
        gone_bt.setOnClickListener(this);
        not_often_use=findViewById(R.id.not_often_use);
        //ViewStub比直接设置visibility效率更高
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gone_bt:
                not_often_use.setVisibility(View.GONE);
                break;
            case R.id.visible_bt:
                not_often_use.setVisibility(View.VISIBLE);
                break;
            case R.id.inflate_bt:
                View inflate = not_often_use.inflate();
                /**
                 * 而不管使用哪种方式，一旦<ViewStub>被设置为可见或是被inflate了，<ViewStub>就不存在了，
                 * 取而代之的是被inflate的Layout，并将这个Layout的ID重新设置为<ViewStub>中通过
                 * android:inflatedld属性所指定的ID，这也是为什么两次调用inflate方法会报错的原因。
                 * */
                /**
                 * setVisibility(View.VISIBLE)可以多次调用
                 * */
                TextView textView=inflate.findViewById(R.id.tv);
                textView.setText("hahahaha");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
