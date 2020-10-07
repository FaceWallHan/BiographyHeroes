package com.example.hero.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.PathParser;

import com.example.hero.R;
import com.example.hero.bean.ProvinceBean;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ChinaMapView extends View {
    private Paint paint;
    private List<ProvinceBean> pathList;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                setPathColor();
            }
        }
    };

    private void setPathColor() {
        for (int i = 0; i < pathList.size(); i++) {
            int color = 0;
            switch (i % 5) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.BLUE;
                    break;
                case 2:
                    color = Color.GREEN;
                    break;
                case 3:
                    color = Color.YELLOW;
                    break;
                case 4:
                    color = Color.MAGENTA;
                    break;
                default:
                    break;
            }
            ProvinceBean bean = pathList.get(i);
            bean.setFillColor(color);
            pathList.set(i, bean);
        }
        //重绘
        postInvalidate();
    }
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
     */
    private void inView() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathList = new ArrayList<>();
        paint.setColor(Color.RED);
        thread.start();

    }

    private Thread thread = new Thread() {
        @Override
        public void run() {
            //Alt+Shift+箭头移动
            try {
                //对China.svg进行解析
                InputStream inputStream = getContext().getResources().openRawResource(R.raw.china);
                //通过DocumentBuilderFactory创建一个DocumentBuilder对象
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                //通过builder对象将svg资源转化为Document对象
                Document document = builder.parse(inputStream);
                //获取根节点
                Element rootElement = document.getDocumentElement();
                //获取根节点下面的path节点
                NodeList nodeList = rootElement.getElementsByTagName("path");
                //遍历所有path节点
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    String pathData = element.getAttribute("d");
                    String fillColor = element.getAttribute("fill");
                    //解析pathData属性数据
                    Path path = PathParser.createPathFromPathData(pathData);
                    ProvinceBean bean = new ProvinceBean(path);
                    //bean.setFillColor(Color.parseColor(fillColor));
                    pathList.add(bean);

                }
                handler.sendEmptyMessage(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        for (ProvinceBean provinceBean : pathList) {
            provinceBean.draw(canvas, paint);
        }
        canvas.restore();
    }
}
