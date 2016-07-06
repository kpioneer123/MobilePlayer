package com.cloudhome.mobileplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.cloudhome.mobileplayer.view.FullVideoView;


/**
 * 视频播放器
 * Created by xionghu on 2016/7/6.
 * Email：965705418@qq.com
 */
public class VideoPlayerActivity extends Activity {

    private FullVideoView videoview;

    /**
     * 视频播放地址
     */
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videopalyer);
        videoview = (FullVideoView) findViewById(R.id.videoview);

        //得到数据
        uri =getIntent().getData();

        videoview.setVideoURI(uri);

        //设置一下监听：播放完的监听，播放准备好的监听，播放出错的监听

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                //开始播放
                videoview.start();
            }
        });

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                Toast.makeText(getApplicationContext(),"视频播放完成了",Toast.LENGTH_SHORT).show();

                finish();//退出播放器
            }
        });

        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(getApplicationContext(),"视频播放出错了",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //设置控制视频的控制面板

        videoview.setMediaController(new MediaController(this));
    }



}
