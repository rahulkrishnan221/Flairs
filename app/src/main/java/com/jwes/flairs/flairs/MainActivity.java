package com.jwes.flairs.flairs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    private LinearLayout vdo;
    private LinearLayout book;
    String x;
    String y;
    private TextView profile;
    public static  String ebook_video="none";
    public static final String preference2="pref2";
    public static final String saveit2="savekey2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book = (LinearLayout) findViewById(R.id.temp);
        vdo = (LinearLayout) findViewById(R.id.temp1);
        profile = (TextView) findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profilefn();
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookfn();
            }
        });
        vdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vdofn();
            }
        });


    }


    public void bookfn() {
        SharedPreferences sf2=getSharedPreferences(preference2,Context.MODE_PRIVATE);


        SharedPreferences.Editor editor2 = sf2.edit();

        editor2.putString(saveit2,"book" );
        editor2.commit();
            startActivity(new Intent(MainActivity.this, subject_choice.class));


    }

    public void vdofn() {

        SharedPreferences sf2=getSharedPreferences(preference2,Context.MODE_PRIVATE);


        SharedPreferences.Editor editor2 = sf2.edit();

        editor2.putString(saveit2,"video" );
        editor2.commit();

        startActivity(new Intent(MainActivity.this, subject_choice.class));
    }

    public void profilefn() {
        startActivity(new Intent(MainActivity.this, Profile.class));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}