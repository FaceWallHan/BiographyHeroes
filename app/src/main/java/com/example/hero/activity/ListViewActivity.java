package com.example.hero.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;
import com.example.hero.adapter.ViewHolderAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView list_view;
    private static final String TAG = "ListViewActivity";
    private int lastVisiblePosition=0;//标记上次滑动位置

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
        List<String>list=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(i+"");
        }
        list_view=findViewById(R.id.list_view);
        ViewHolderAdapter adapter=new ViewHolderAdapter(list,this);
        list_view.setAdapter(adapter);
        setListener();
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setListener(){

        /**list_view.setSelection(10);
         * 瞬间移动
         * */
        list_view.postDelayed(new Runnable() {
            @Override
            public void run() {
//                list_view.smoothScrollByOffset(5);//移动offset个item
//                list_view.smoothScrollBy(100,2*1000);//在2s内移动100px
//                list_view.smoothScrollToPosition(15);//移动到第position个item
                /**平滑移动*/
            }
        },300);
        list_view.setEmptyView(findViewById(R.id.empty_img));

        list_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //触摸时操作
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //移动时操作
                        break;
                    case MotionEvent.ACTION_UP:
                        //离开时操作
                        break;
                }
                return false;
            }
        });
        list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        Log.d(TAG, "SCROLL_STATE_IDLE: 滑动停止时");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.d(TAG, "SCROLL_STATE_TOUCH_SCROLL: 正在滚动");
                        break;
                    case SCROLL_STATE_FLING:
                        Log.d(TAG, "SCROLL_STATE_FLING: 手指抛动时，即手指用力滑动................在离开ListView后由于惯性继续滑动");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                                //当前能看到的第一个item的position，当前能看到的item总数，整个listView的item总数(包括没有显示完整的)
                //Log.d(TAG, "onScroll: 滚动时一直调用"+"firstVisibleItem:"+firstVisibleItem+"visibleItemCount:"+visibleItemCount+"totalItemCount:"+totalItemCount);
                if (firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount>0){
                    Log.d(TAG, "onScroll:滚动到最后一行");
                }

                if (firstVisibleItem<lastVisiblePosition){
                    Log.d(TAG, "onScroll:上滑"+"firstVisibleItem"+firstVisibleItem+"lastVisiblePosition"+lastVisiblePosition);
                }else if (firstVisibleItem>lastVisiblePosition){
                    Log.d(TAG, "onScroll:下滑"+"firstVisibleItem"+firstVisibleItem+"lastVisiblePosition"+lastVisiblePosition);
                }
                lastVisiblePosition=firstVisibleItem;
            }
        });
    }
    /**
     * android:dividerHeight="5dp"
     * android:divider="#fff000"
     * android:divider="@null"
     * 隐藏滚动条：android:scrollbars="none"
     * 取消item的点击(动画)效果:android:listSelector="#00000000|@android:color/transparent"
     * 使用adapter.notifyDataSetChanged()时，必须保证传进adapter的数据是同一个list对象而不能是其他对象，否则无法实现该效果
     * list_view.get(Last/First)VisiblePosition();
     * */
}
