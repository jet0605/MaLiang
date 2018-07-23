package com.example.aiiage.drawerlayoutdemo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginActivity extends AppCompatActivity {
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.bt_go)
    Button bt_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }//隐藏标题栏
        ButterKnife.bind(this);
    }
    @OnClick({R.id.fab,R.id.bt_go})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                Intent intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_go:
                Intent intent2 = new Intent(loginActivity.this,TestActivity.class);
                startActivity(intent2);
                break;
                default:
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(loginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
