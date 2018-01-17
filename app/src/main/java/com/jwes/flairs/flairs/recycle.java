package com.jwes.flairs.flairs;

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

public class recycle extends AppCompatActivity {

    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private int columns=2;
    ProgressDialog loading;
    public static final String preference4="ref4";
    public static final String saveit4="savekey4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //Recycler View
        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        mBlogList.setLayoutManager(new GridLayoutManager(this,columns));

        //send a Query to the database
        loading= ProgressDialog.show(recycle.this,"Please wait","Hold on......",true,true);

        database=FirebaseDatabase.getInstance();
        SharedPreferences sf3=getSharedPreferences(preference3,Context.MODE_PRIVATE);
        String book_cover = sf3.getString(saveit3,"");
        myRef=database.getReference(book_cover);
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
                 final String post_key=getRef(position).getKey();

                 viewHolder.setTitle(model.getTitle());
                         viewHolder.setImage(getApplicationContext(), model.getImage());
                 loading.dismiss();
                 viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         SharedPreferences sf4=getSharedPreferences(preference4,Context.MODE_PRIVATE);

                         SharedPreferences.Editor editor4=sf4.edit();
                         editor4.putString(saveit4,post_key);
                         editor4.commit();
                         Toast.makeText(recycle.this, post_key, Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(recycle.this,pdf.class));

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

                TextView post_title = (TextView) mView.findViewById(R.id.titleText);
            post_title.setText(title);
        }
        public void setImage(Context ctx,String image)
        {
                ImageView post_image = (ImageView) mView.findViewById(R.id.imageViewy);
                //We pass context
                Picasso.with(ctx).load(image).into(post_image);
        }

    }

}
