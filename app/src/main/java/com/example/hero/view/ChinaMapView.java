package com.example.hero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.example.hero.net.NetCall;
import com.example.hero.net.NetRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChinaMapView extends View {
    private Paint paint;
    private List<Path>pathList;
    public ChinaMapView(Context context) {
        super(context);
        inView();
    }

    public ChinaMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inView();
    }

    public ChinaMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView();
    }
    /**
     * 1.获取raw文件夹下的svg资源
     * 2.解析svg资源
     * 3.初始画笔
     * */
    private void inView(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        pathList=new ArrayList<>();
        paint.setColor(Color.RED);
        //request.start();

    }
    private void handSubString(String string){
        new Thread(){
            @Override
            public void run() {
                super.run();
                if (string==null||string.equals("")){
                    return;
                }
                StringBuilder secondHand=new StringBuilder();
                int length=string.length();
                String hand=string.substring(0,length-2);//后面两个]]
                String handAgain=hand.substring(2);
                String []path=handAgain.split(",");
                for (int i = 0; i < path.length; i++) {
                    if (i%2!=0){
                        secondHand.append(path[i-1])
                                .append(",")
                                .append(path[i ]);
                    }
                }
//        int index=2%2;
                Log.d("1111111111111111", "handSubString: "+secondHand.toString());
            }
        }.start();

    }
    private NetRequest request=new NetRequest()
            .showDialog(getContext())
            .setUrl("chinaHigh.json")
            .setNetCall(new NetCall() {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    try {
                        JSONArray array=new JSONArray(jsonObject.getString("features"));
                        Log.d("1111111111111111", "onSuccess: "+array.length());
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object=array.getJSONObject(i);
                            JSONObject geometryObject=new JSONObject(object.getString("geometry"));

                            Log.d("11111111111", "------------------: "+geometryObject.getString("coordinates"));
                            String coordinates=geometryObject.getString("coordinates");
                            //Path path= PathParser.createPathFromPathData(geometryObject.getString("coordinates"));
                            JSONObject propertiesArray=new JSONObject(object.getString("properties"));
                            Log.d("1111111111111111", "propertiesArray: "+propertiesArray.getString("name"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("1111111111111111", "catch: "+e.getLocalizedMessage());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("1111111111", "onFailure: "+t.getMessage());
                }
            });
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
//        for (Path path : pathList) {
//            canvas.drawPath(path,paint);
//        }
//        canvas.restore();
    }
}
