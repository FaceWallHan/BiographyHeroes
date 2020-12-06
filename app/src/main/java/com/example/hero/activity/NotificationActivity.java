package com.example.hero.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.hero.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup visibility_radio_group;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        inView();
    }
    public interface OnNotificationListener{
        void listen(Notification.Builder builder);
    }
    private void inView(){
        Button basic=findViewById(R.id.basic);
        basic.setOnClickListener(this);
        Button collapsed=findViewById(R.id.collapsed);
        collapsed.setOnClickListener(this);
        Button heads_up=findViewById(R.id.heads_up);
        heads_up.setOnClickListener(this);
        Button visibility_bt=findViewById(R.id.visibility_bt);
        visibility_bt.setOnClickListener(this);
        visibility_radio_group=findViewById(R.id.visibility_radio_group);
    }
    private void showNotification(OnNotificationListener onNotificationListener){
        Notification.Builder builder=new Notification.Builder(NotificationActivity.this);
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/"));
        PendingIntent pendingIntent=PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
        builder.setSmallIcon(R.drawable.a);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.d));
        builder.setContentTitle("basic notification");
        builder.setContentText("i am a basic notification");
        builder.setSubText("it is a really basic")
        .setDefaults(NotificationCompat.DEFAULT_ALL);
        if (onNotificationListener!=null){
            onNotificationListener.listen(builder);
        }
        builder.setCategory(Notification.CATEGORY_ERROR);
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.basic:
                showNotification(null);
                break;
            case R.id.collapsed:
                showNotification(new OnNotificationListener() {
                    @Override
                    public void listen(Notification.Builder builder) {
                        //通过RemoteViews来创建新的Notification视图
                        RemoteViews contentView=new RemoteViews(getPackageName(),R.layout.notification_item_layout);
                        contentView.setTextViewText(R.id.textView,"show me when collapsed");
                        Notification build = builder.build();
                        build.contentView=contentView;
                        build.bigContentView= new RemoteViews(getPackageName(),R.layout.notification_expanded_layout);
                    }
                });
                break;
            case R.id.heads_up:
                showNotification(new OnNotificationListener() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void listen(Notification.Builder builder) {
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bilibili.com/"));
                        PendingIntent pendingIntent=PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
                        builder.setFullScreenIntent(pendingIntent,true);
                        builder.setPriority(NotificationCompat.PRIORITY_MAX);
                        builder.setColor(Color.RED);
                    }
                });
                break;
            case R.id.visibility_bt:
                showNotification(new OnNotificationListener() {
                    @Override
                    public void listen(Notification.Builder builder) {
                        switch (visibility_radio_group.getCheckedRadioButtonId()){
                            case R.id.radio_button_public:
                                //当没有锁屏的时候会显示
                                builder.setContentText("public");
                                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                                break;
                            case R.id.radio_button_private:
                                //在任何情况下都会显示
                                builder.setContentText("private");
                                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                                break;
                            case R.id.radio_button_secret:
                                //在pin、password,等安全锁和没有锁屏的情况下才会显示
                                builder.setContentText("secret");
                                builder.setVisibility(Notification.VISIBILITY_SECRET);
                                break;
                        }
                    }
                });

                break;
        }
    }
}
