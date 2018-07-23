package com.example.aiiage.drawerlayoutdemo;

import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.aiiage.drawerlayoutdemo.adapter.ContentAdapter;
import com.example.aiiage.drawerlayoutdemo.model.ContentModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.left_listview)
    ListView listView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.leftmenu)
    ImageView menuLeft;
    @BindView(R.id.rightmenu)
   ImageView menuRight;

    private Adapter adapter;
    private List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }//隐藏标题栏
        initData();
        ButterKnife.bind(this);
        adapter = new ContentAdapter(this,list);
        listView.setAdapter((ListAdapter) adapter);
    }
    private void initData(){
        list = new ArrayList<ContentModel>();
        list.add(new ContentModel(R.drawable.friends, "好友", 1));
        list.add(new ContentModel(R.drawable.message, "消息", 2));
        list.add(new ContentModel(R.drawable.collection, "收藏", 3));
        list.add(new ContentModel(R.drawable.cache, "缓存", 4));
        list.add(new ContentModel(R.drawable.setting, "设置", 5));
        list.add(new ContentModel(R.drawable.about, "关于", 6));
    }
    @OnClick({R.id.leftmenu,R.id.rightmenu})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.leftmenu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.rightmenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            default:
        }
    }
}