package com.jwes.flairs.flairs;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;


public class VideoLandscape extends AppCompatActivity {

    MediaController mediaC;
    VideoView videov;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_landscape);
        videov=(VideoView)findViewById(R.id.videoView);
        mediaC=new MediaController(this);
        String videopath="https://firebasestorage.googleapis.com/v0/b/flairs-6fa83.appspot.com/o/video%2Ftest.mp4?alt=media&token=ebd91607-64e0-4eda-8ced-25ae51b6b24a";
        Uri uri=Uri.parse(videopath);
        videov.setVideoURI(uri);
        videov.setMediaController(mediaC);
        mediaC.setAnchorView(videov);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videov.start();


    }

    private RelativeLayout.LayoutParams paramsNotFullscreen; //if you're using RelativeLatout

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) //To fullscreen
        {
            paramsNotFullscreen=(RelativeLayout.LayoutParams)videov.getLayoutParams();
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height= ViewGroup.LayoutParams.MATCH_PARENT;
            params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            videov.setLayoutParams(params);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            videov.setLayoutParams(paramsNotFullscreen);
        }
    }
}
