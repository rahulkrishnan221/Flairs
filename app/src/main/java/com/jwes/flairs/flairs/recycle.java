package com.jwes.flairs.flairs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class recycle extends AppCompatActivity {

    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private int row_count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //Recycler View
        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        //send a Query to the database

        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Data");
    }

    @Override
    protected void onStart()
    {

        super.onStart();
        FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<ModelClass, BlogViewHolder>(
                ModelClass.class,
                R.layout.design_row,
                BlogViewHolder.class,
                myRef)
         {
             @Override
             protected void populateViewHolder(BlogViewHolder viewHolder, ModelClass model,int position)
             {
                         viewHolder.setTitle(model.getTitle(),position);
                         viewHolder.setImage(getApplicationContext(), model.getImage(),position);
                         row_count++;
             }
        };
       if((row_count%2)!=0) {
            mBlogList.setAdapter(firebaseRecyclerAdapter);
        }


    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private int text_count=1;
        private int image_count=1;
        public BlogViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in/"));
                    Intent browserChooserIntent=Intent.createChooser(browserIntent,"Choose browser of your choice");
                    v.getContext().startActivity(browserChooserIntent);

                }
            });
        }

        public void setTitle(String title,int pos) {
            if ((pos % 2) == 0)
            {
                TextView post_title = (TextView) mView.findViewById(R.id.titleText);
            post_title.setText(title);
        }
        else
            {
                TextView post_title = (TextView) mView.findViewById(R.id.titleText1);
                post_title.setText(title);
            }

        }
        public void setImage(Context ctx,String image,int pos)
        {
            if ((pos % 2) == 0) {
                ImageView post_image = (ImageView) mView.findViewById(R.id.imageViewy);
                //We pass context
                Picasso.with(ctx).load(image).into(post_image);
            }
            else
            {
                ImageView post_image = (ImageView) mView.findViewById(R.id.imageViewx);
                //We pass context
                Picasso.with(ctx).load(image).into(post_image);
            }
        }
    }

}
