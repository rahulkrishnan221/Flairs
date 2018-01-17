package com.jwes.flairs.flairs;

/**
 * Created by rahul on 17/1/18.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.jwes.flairs.flairs.subject_choice.preference3;
import static com.jwes.flairs.flairs.subject_choice.saveit3;

public class recycle_video extends AppCompatActivity {

    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public static final String preference5="ref5";
    public static final String saveit5="savekey5";

    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //Recycler View
        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        //send a Query to the database
        loading= ProgressDialog.show(recycle_video.this,"Please wait","Hold on......",true,true);

        database=FirebaseDatabase.getInstance();
        SharedPreferences sf3=getSharedPreferences(preference3,Context.MODE_PRIVATE);
        String video_cover = sf3.getString(saveit3,"");
        myRef=database.getReference(video_cover);
    }

    @Override
    protected void onStart()
    {

        super.onStart();
        FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>(
                ModelClass.class,
                R.layout.activity_video_list,
                BlogViewHolder.class,
                myRef)
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, ModelClass model,int position)
            {

                final String post_key=getRef(position).getKey();


                viewHolder.setTitle(model.getTitle());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                loading.dismiss();
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences sf5=getSharedPreferences(preference5,Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor5=sf5.edit();
                        editor5.putString(saveit5,post_key);
                        editor5.commit();
                        Toast.makeText(recycle_video.this, post_key, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(recycle_video.this,VideoLandscape.class));

                    }
                });
            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;

        }

        public void setTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.videotitle);
            post_title.setText(title);
        }
        public void setImage(Context ctx,String image)
        {
            ImageView post_image = (ImageView) mView.findViewById(R.id.videothubnail);
            //We pass context
            Picasso.with(ctx).load(image).into(post_image);
        }

    }

}

