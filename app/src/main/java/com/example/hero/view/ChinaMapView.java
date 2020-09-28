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

import com.example.hero.net.NetCall;
import com.example.hero.net.NetRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
        request.start();
    }
    private NetRequest request=new NetRequest()
            .showDialog(getContext())
            .setUrl("chinaHigh.json")
            .setNetCall(new NetCall() {
                @Override
                public void onSuccess(JSONObject jsonObject) {
                    try {
                        JSONArray array=new JSONArray(jsonObject.getString("features"));
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object=array.getJSONObject(i);
                            JSONObject geometryObject=new JSONObject(object.getString("geometry"));
                            JSONObject coordinatesArray=new JSONObject(geometryObject.getString("coordinates"));
                            //Log.d("1111111111111111", "onSuccess: "+coordinatesArray.toString());
                            JSONObject propertiesArray=new JSONObject(object.getString("properties"));
                            //Log.d("1111111111111111", "onSuccess: "+propertiesArray.getString("name"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("1111111111", "catch: "+e.getLocalizedMessage());
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
