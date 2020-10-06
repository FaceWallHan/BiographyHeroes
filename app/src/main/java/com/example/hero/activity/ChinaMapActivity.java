package com.example.hero.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hero.R;

public class ChinaMapActivity extends AppCompatActivity {
    private static final String TAG = "ChinaMapActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.china_map_layout);
        handSub("很快，就有人被搜刮一空，再也拿不出钱了，然后全家都被下一波清兵给杀的干干净净。慢慢的" +
                "，被杀的人越来越多，还能交出钱的人越来越少。很多人没了钱，就把自己藏的严严实实，但扬州城就那" +
                "么大，能藏人的地方就那么点，绝大多数人都找不到安全的藏身之地。更何况，还有不少本地人，主动给清" +
                "兵当向导去搜寻藏匿之人，只求自己能活命。动手杀人的清兵越来越多，还能侥幸活下去的扬州人越来越少。" +
                "榨不出钱财后，清兵们就把男人和样貌普通的女人给屠杀一空，而把那些有姿色的女人给搜集起来。" +
                "作者：一颗青木著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",20);
    }
    private void handSub(String str,int num){
        if (str==null||str.equals("")){
            return;
        }
        int lines = (str.length() + (num - 1)) / num;
        /**
         * 中间行
         */
        int center = ((lines + 1) / 2);
        Log.d(TAG, "长度:" + str.length());
        Log.d(TAG, "可以分为:" + lines + "行");
        /**
         * 转成数组
         */
        char[] chars = str.toCharArray();
        /**
         * 计数
         */
        int index = 0;
        /**
         * 开始加工
         */
        for (int i = 0; i < chars.length; i += num) {
            index++;
            Log.d(TAG, "中间行=" + center + "当前行:" + index);
            String string = String.valueOf(chars[i]);
            for (int k = 1; k <= num - 1; k++) {
                if (i + k < chars.length)
                    string = string.concat(String.valueOf(chars[i + k]));
            }
            //标示中间行
            if (index == center) {
                System.out.print("--->");
            }
            //打印结果 或者用String[] 来接 在循环外返回一个数组出去
            Log.d(TAG, string);
        }
    }
}
