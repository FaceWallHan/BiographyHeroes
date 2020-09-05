package com.example.hero;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout {
    private int leftTextColor,rightTextColor,titleTextColor;
    private float titleTextSize;
    private Drawable leftBackground,rightBackground;
    private String leftText,rightText,title;
    private Button leftButton ,rightButton;
    private TextView titleView;
    private RelativeLayout.LayoutParams leftParams,rightParams,titleParams;
    private topBarClickListener topBarClickListener;

    public void setTopBarClickListener(TopBar.topBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

    public TopBar(Context context) {
        super(context);

    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inView(context,attrs);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inView(context,attrs);
    }

//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//
//    }
    public interface  topBarClickListener{
        void leftClick();
        void rightClick();
    }
    private void inView(Context context, AttributeSet attrs){
        @SuppressLint("Recycle")
        //通过此方法，将在attrs.xml中定义的declare-styleable所有属性的值存储到TypedArray中
                TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        leftTextColor=ta.getColor(R.styleable.TopBar_leftTextColor,0);
        leftBackground=ta.getDrawable(R.styleable.TopBar_leftBackground);
        leftText=ta.getString(R.styleable.TopBar_leftText);

        rightTextColor=ta.getColor(R.styleable.TopBar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.TopBar_rightBackground);
        rightText=ta.getString(R.styleable.TopBar_rightText);

        titleTextSize=ta.getDimension(R.styleable.TopBar_titleTextSize,10);
        titleTextColor=ta.getColor(R.styleable.TopBar_titleTextColor,0);
        title=ta.getString(R.styleable.TopBar_title);

        ta.recycle();//获取完所有属性值后调用recycle()来完成资源回收
        leftButton=new Button(context);
        rightButton=new Button(context);
        titleView=new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        titleView.setTextSize(titleTextSize);
        titleView.setTextColor(titleTextColor);
        titleView.setText(title);
        titleView.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //添加到ViewGroup
        //rule：规则
        addView(leftButton,leftParams);

        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightParams);

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(titleView,titleParams);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                topBarClickListener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                topBarClickListener.rightClick();
            }
        });
    }
    //设置按钮的显示与否通过id区分按钮，flag区分是否显示
    public void setButtonVisible(int id,boolean flag){
        if (flag){
            if (id==0){
                leftButton.setVisibility(VISIBLE);
            }else {
                rightButton.setVisibility(VISIBLE);
            }
        }else {
            if (id==0){
                leftButton.setVisibility(GONE);
            }else {
                rightButton.setVisibility(GONE);
            }
        }
    }
//    private int leftTextColor,rightTextColor,titleTextColor;
//    private float titleTextSize;
//    private Drawable leftBackground,rightBackground;
//    private String leftText,rightText,title;
//    private Button leftButton ,rightButton;
//    private TextView titleView;
//    private LayoutParams leftParams,rightParams,titleParams;
//    private topBarClickListener topBarClickListener;
//
//    public void setTopBarClickListener(TopBar.topBarClickListener topBarClickListener) {
//        this.topBarClickListener = topBarClickListener;
//    }
//
//    public interface  topBarClickListener{
//        void leftClick();
//        void rightClick();
//    }
//    private void configurationAttributes(Context context, AttributeSet attrs){
//
//        //通过此方法，将在attrs.xml中定义的declare-styleable所有属性的值存储到TypedArray中
//        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
//        leftTextColor=ta.getColor(R.styleable.TopBar_leftTextColor,0);
//        leftBackground=ta.getDrawable(R.styleable.TopBar_leftBackground);
//        leftText=ta.getString(R.styleable.TopBar_leftText);
//
//        rightTextColor=ta.getColor(R.styleable.TopBar_rightTextColor,0);
//        rightBackground=ta.getDrawable(R.styleable.TopBar_rightBackground);
//        rightText=ta.getString(R.styleable.TopBar_rightText);
//
//        titleTextSize=ta.getDimension(R.styleable.TopBar_titleTextSize,10);
//        titleTextColor=ta.getColor(R.styleable.TopBar_titleTextColor,0);
//        title=ta.getString(R.styleable.TopBar_title);
//
//        ta.recycle();//获取完所有属性值后调用recycle()来完成资源回收
//    }
//    private void inView(Context context){
//
//        leftButton=new Button(context);
//        rightButton=new Button(context);
//        titleView=new TextView(context);
//
//        leftButton.setTextColor(leftTextColor);
//        leftButton.setBackground(leftBackground);
//        leftButton.setText(leftText);
//
//        rightButton.setTextColor(rightTextColor);
//        rightButton.setBackground(rightBackground);
//        rightButton.setText(rightText);
//
//        titleView.setText(title);
//        titleView.setTextSize(titleTextSize);
//        titleView.setTextColor(titleTextColor);
//        titleView.setGravity(Gravity.CENTER);
//
//        //为组件元素设置相应的布局元素
//        leftParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
//        //添加到ViewGroup
//        //rule：规则
//        addView(leftButton,leftParams);
//
//        rightParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
//        addView(rightButton,rightParams);
////
//        titleParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
//        addView(titleView,titleParams);
//        leftButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                topBarClickListener.leftClick();
//            }
//        });
//        rightButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                topBarClickListener.rightClick();
//            }
//        });
//    }
//    //设置按钮的显示与否通过id区分按钮，flag区分是否显示
//    public void setButtonVisible(int id,boolean flag){
//        if (flag){
//            if (id==0){
//                leftButton.setVisibility(VISIBLE);
//            }else {
//                rightButton.setVisibility(VISIBLE);
//            }
//        }else {
//            if (id==0){
//                leftButton.setVisibility(GONE);
//            }else {
//                rightButton.setVisibility(GONE);
//            }
//        }
//    }
//
//    public TopBar(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    public TopBar(Context context) {
//        super(context);
//    }
//
//    public TopBar(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        configurationAttributes(context,attrs);
//        inView(context);
//
//    }
}
