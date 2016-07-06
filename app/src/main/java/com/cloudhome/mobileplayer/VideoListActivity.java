package com.cloudhome.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudhome.mobileplayer.adapter.VideoListAdapter;
import com.cloudhome.mobileplayer.bean.VideoItemData;

import java.util.ArrayList;
/**
 * Created by xionghu on 2016/7/5.
 * Email：965705418@qq.com
 */
public class VideoListActivity extends Activity {


    private ListView lv_videolist;

    private TextView lv_videolist_novideo;

    private ArrayList<VideoItemData> videoItems;
    private VideoListAdapter madapter;

    private android.os.Handler handler =new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(videoItems !=null && videoItems.size()>0)
            {
                //有视频信息
                lv_videolist_novideo.setVisibility(View.GONE);
                madapter = new VideoListAdapter(VideoListActivity.this,videoItems);
                //显示在ListView中
                lv_videolist.setAdapter(madapter);
            }else{
                //有视频
                lv_videolist_novideo.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolist);


        initView();
        getData();
        setListener();
    }

    private void setListener() {

        lv_videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                VideoItemData item = videoItems.get(position);

                Toast.makeText(VideoListActivity.this,item.getName(),Toast.LENGTH_SHORT).show();
//               // 把手机里面所有的播放器调起来-隐式意图
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                Log.d("7777",item.getData()+"999");
//
//                String strend="";
//                String videoPath = item.getData();
//                if(videoPath.toLowerCase().endsWith(".mp4")){
//                    strend="mp4";
//                }
//                else if(videoPath.toLowerCase().endsWith(".3gp")){
//                    strend="3gp";
//                }
//                else if(videoPath.toLowerCase().endsWith(".mov")){
//                    strend="mov";
//                }
//                else if(videoPath.toLowerCase().endsWith(".wmv")){
//                    strend="wmv";
//                }
//
//                intent.setDataAndType(Uri.parse(videoPath),"video/"+strend);

                Intent intent =new Intent(VideoListActivity.this,VideoPlayerActivity.class);
                intent.setData(Uri.parse(item.getData()));
                startActivity(intent);
            }
        });
    }

    /**
     * 得到手机的视频
     */
    private void getData() {

       new Thread(){
              public void run()
        {
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            //读取手机里面的所有的视频

            String[] projection = {MediaStore.Video.Media.DISPLAY_NAME,//视频的名称
                    MediaStore.Video.Media.DURATION,//视频的时长
                    MediaStore.Video.Media.SIZE,//视频的大小
                    MediaStore.Video.Media.DATA,//视频的播放地址
            };

            //读取手机里面所有的资源
          Cursor cursor =   getContentResolver().query(uri,projection,null,null,null);
            videoItems = new ArrayList<VideoItemData>();
            while (cursor.moveToNext())
            {
                VideoItemData videoItemData = new VideoItemData();
                String name    = cursor.getString(0);  //视频的名称
                videoItemData.setName(name);
                long duration  = cursor.getLong(1);    //视频的长度
                videoItemData.setDuration(duration);
                long size      = cursor.getLong(2);    //视频的大小
                videoItemData.setSize(size);
                String data    = cursor.getString(3);  //视频的播放地址

                videoItemData.setData(data);
                videoItems.add(videoItemData);
            }

            cursor.close();//关闭
            handler.sendEmptyMessage(0);
        }
       }.start();

    }

    /**
     * 初始化View
     */
    private void initView() {
        lv_videolist = (ListView) findViewById(R.id.lv_videolist);
        lv_videolist_novideo = (TextView) findViewById(R.id.lv_videolist_novideo);
    }
}
