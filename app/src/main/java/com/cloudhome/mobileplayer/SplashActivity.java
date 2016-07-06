package com.cloudhome.mobileplayer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

/**
 * 启动页面
 * Created by xionghu on 2016/7/5.
 * Email：965705418@qq.com
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startVideoList();
            }
        },2000);

    }

    /**
     * 是否已经启动了视频列表
     * true：启动
     * false：没有启动
     */
    private  boolean isStartVideoList = false;

        private void startVideoList() {
            if(!isStartVideoList) {
                isStartVideoList = true;
                Intent intent = new Intent(this, VideoListActivity.class);
                startActivity(intent);
                finish();
            }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        startVideoList();
        return super.onTouchEvent(event);

    }
}
