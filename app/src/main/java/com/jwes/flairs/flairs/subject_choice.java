package com.jwes.flairs.flairs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import static com.jwes.flairs.flairs.MainActivity.ebook_video;
import static com.jwes.flairs.flairs.MainActivity.preference2;
import static com.jwes.flairs.flairs.MainActivity.saveit2;
import static com.jwes.flairs.flairs.branch_year.preference;
import static com.jwes.flairs.flairs.branch_year.preference1;
import static com.jwes.flairs.flairs.branch_year.saveit;
import static com.jwes.flairs.flairs.branch_year.saveit1;

public class subject_choice extends AppCompatActivity {



    private RecyclerView mBlogList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private int columns=2;
    ProgressDialog loading;
    public static final String preference3="pref3";
    public static final String saveit3="savekey3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //Recycler View
        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        mBlogList.setLayoutManager(new GridLayoutManager(this,columns));

        //send a Query to the database
        loading= ProgressDialog.show(subject_choice.this,"Please wait","Hold on......",true,true);
        database=FirebaseDatabase.getInstance();


        SharedPreferences sf2=getSharedPreferences(preference2,Context.MODE_PRIVATE);
        SharedPreferences sf=getSharedPreferences(preference,Context.MODE_PRIVATE);
        SharedPreferences sf1=getSharedPreferences(preference1,Context.MODE_PRIVATE);
        String book_video = sf2.getString(saveit2,"");
        String department=sf.getString(saveit,"");
        String year=sf1.getString(saveit1,"");
        //Start the same Activity

        String ref=book_video+"_"+department+"_"+year;
        myRef=database.getReference(ref);

    }

    @Override
    protected void onStart()
    {

        super.onStart();
        FirebaseRecyclerAdapter<ModelClass_subject, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<ModelClass_subject, BlogViewHolder>(
                ModelClass_subject.class,
                R.layout.activity_subject_choice,
                BlogViewHolder.class,
                myRef)
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, ModelClass_subject model,int position)
            {
                final String post_key=getRef(position).getKey();

                viewHolder.setTitle1(model.getTitle1());
                viewHolder.setCode(model.getCode());
                loading.dismiss();
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(subject_choice.this, post_key, Toast.LENGTH_SHORT).show();
                        SharedPreferences sf3=getSharedPreferences(preference3,Context.MODE_PRIVATE);


                        SharedPreferences.Editor editor3 = sf3.edit();

                        editor3.putString(saveit3,post_key );
                        editor3.commit();
                        startActivity(new Intent(subject_choice.this,recycle.class));



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

        public void setTitle1(String title1) {

            TextView post_title = (TextView) mView.findViewById(R.id.titleText1);
            post_title.setText(title1);
        }
        public void setCode(String code)
        {
            TextView post_code=(TextView)mView.findViewById(R.id.code);
            post_code.setText(code);

        }






    }

}
