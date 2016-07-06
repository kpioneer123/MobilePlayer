package com.cloudhome.startallvideoplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void startAllVideoPlayer(View view)
    {
        Toast.makeText(MainActivity.this,"调起所有播放器",Toast.LENGTH_SHORT).show();
        // 把手机里面所有的播放器调起来-隐式意图
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("/storage/emulated/0/DCIM/Camera/VID_20160706_143923.mp4"),"video/*");
        startActivity(intent);
    }
}
