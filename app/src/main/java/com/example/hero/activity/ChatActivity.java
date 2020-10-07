package com.example.hero.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.bean.ChatBean;
import com.example.hero.R;
import com.example.hero.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    private List<ChatBean> list;
    private Spinner type_list;
    private EditText send_content;
    private Button send;
    private ChatAdapter adapter;
    private ListView chatList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        inView();
        addData();
        setListener();

    }
    private void addData(){
        list.clear();
        boolean[] types ={true,false};
        String[] contents ={"孔子 孟子 老子，你知道你最适合当什么子吗?”“不知道。”“我的妻子。"
                        ,"人生呐，最美好的两件事，就是睡你——睡觉，和你"
                        ,"你会看手相吗?”“会一点，你命里缺我"
                        ,"你知道我喜欢谁吗，不知道就看看第一个字。"
                        ,"我对你的爱很宽，愿你在我的爱里，一生都迷路。"};
        Random random=new Random();
        for (int i = 1; i <=10; i++) {
            list.add(new ChatBean(types[random.nextInt(types.length)],
                    contents[random.nextInt(contents.length)]));
        }
        adapter=new ChatAdapter(ChatActivity.this,list);
        chatList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void setListener(){
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type=type_list.getSelectedItem().toString();
                String content=send_content.getText().toString().trim();
                if (!content.equals("")){
                    boolean index=type.equals("发送");
                    list.add(new ChatBean(index,content));
                    adapter.notifyDataSetChanged();
                    chatList.setSelection(list.size()-1);
                    send_content.setText("");
                }else {
                    Toast.makeText(ChatActivity.this, "输入为空！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setCurrentItem(i);
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void inView(){
        list=new ArrayList<>();
        chatList = findViewById(R.id.chatList);
        send_content=findViewById(R.id.send_content);
        type_list=findViewById(R.id.type_list);
        type_list.setSelection(0);
        send=findViewById(R.id.send);
    }
}
