package com.example.aiiage.drawerlayoutdemo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aiiage.drawerlayoutdemo.adapter.ContentAdapter;
import com.example.aiiage.drawerlayoutdemo.adapter.FeedAdapter;
import com.example.aiiage.drawerlayoutdemo.model.ContentModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener{
    @BindView(R.id.left_listview)
    ListView listView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.leftmenu)
    ImageView menuLeft;
    @BindView(R.id.rightmenu)
    ImageView menuRight;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.content)
    CoordinatorLayout clContent;
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;
    private Adapter adapter;
    private List list;
    private FeedAdapter feedAdapter;
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
        feedAdapter.updateItems(false);
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
    @OnClick({R.id.leftmenu,R.id.rightmenu,R.id.tv_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.leftmenu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.rightmenu:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.tv_login:
                tv_login.setTextColor(Color.RED);
                //Toast.makeText(MainActivity.this,"to login",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,loginActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(this);
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
    }
    public void showLikedSnackbar() {
        Snackbar.make(clContent, "Liked!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentsClick(View v, int position) {

    }

    @Override
    public void onMoreClick(View v, int position) {

    }

    @Override
    public void onProfileClick(View v) {

    }
}