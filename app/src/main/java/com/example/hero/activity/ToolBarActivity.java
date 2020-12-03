package com.example.hero.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hero.R;

import java.util.Objects;

public class ToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_main_layout);
        inView();
    }
    private void inView(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.d);
        toolbar.setTitle("主标题");
        toolbar.setSubtitle("副标题");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.description, R.string.format);
        toggle.syncState();
        drawer.setDrawerListener(toggle);
        // 菜单的监听可以在toolbar里设置，
        // 也可通过Activity的onOptionsItemSelected回调方法来处理
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_setting:
                        Toast.makeText(ToolBarActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.action_share));
        /**
         * */
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        actionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }
}
