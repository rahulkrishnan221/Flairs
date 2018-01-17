package com.jwes.flairs.flairs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.jwes.flairs.flairs.recycle.preference4;
import static com.jwes.flairs.flairs.recycle.saveit4;
import static com.jwes.flairs.flairs.recycle_video.preference5;
import static com.jwes.flairs.flairs.recycle_video.saveit5;


public class VideoLandscape extends AppCompatActivity {

    MediaController mediaC;
    VideoView videov;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    String videopath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_landscape);
        videov=(VideoView)findViewById(R.id.videoView);
        mediaC=new MediaController(this);

        database=FirebaseDatabase.getInstance();
        SharedPreferences sf5=getSharedPreferences(preference5, Context.MODE_PRIVATE);
        String video_link = sf5.getString(saveit5,"");
        myRef=FirebaseDatabase.getInstance().getReference().child(video_link);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                videopath=dataSnapshot.getValue(String.class);
                Uri uri=Uri.parse(videopath);
                videov.setVideoURI(uri);
                videov.setMediaController(mediaC);
                mediaC.setAnchorView(videov);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                videov.start();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    //    String videopath="https://firebasestorage.googleapis.com/v0/b/flairs-6fa83.appspot.com/o/video%2Ftest.mp4?alt=media&token=ebd91607-64e0-4eda-8ced-25ae51b6b24a";



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
