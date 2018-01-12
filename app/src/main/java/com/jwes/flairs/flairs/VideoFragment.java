package com.jwes.flairs.flairs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import static android.R.attr.button;


public class VideoFragment extends Fragment{
    private LinearLayout video;

    public static VideoFragment newInstance() {
    VideoFragment fragment = new VideoFragment();

    return fragment;
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video,
                container, false);

        video=(LinearLayout)view.findViewById(R.id.videoact);
        video.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                videocall();
            }
        });
        //Button play = (Button) view.findViewById(R.id.play);

        //play.setOnClickListener(new View.OnClickListener()
        //{
          //  @Override
            //public void onClick(View v)
            //{
              //videoplay();
            //}
        //});


        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();

        return view;
    }

    public void videocall()
    {


    }




    /**public void videoplay()
    {
        Intent i = new Intent(getActivity(), VideoLandscape.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);

    }**/




}
