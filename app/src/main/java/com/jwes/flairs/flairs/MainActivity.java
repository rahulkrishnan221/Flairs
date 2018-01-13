package com.jwes.flairs.flairs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.jwes.flairs.flairs.branch_year.preference;
import static com.jwes.flairs.flairs.branch_year.preference1;
import static com.jwes.flairs.flairs.branch_year.saveit;
import static com.jwes.flairs.flairs.branch_year.saveit1;

public class MainActivity extends AppCompatActivity {

    private LinearLayout vdo;
    private LinearLayout book;
    String x;
    String y;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book=(LinearLayout)findViewById(R.id.temp);
        vdo=(LinearLayout)findViewById(R.id.temp1);
        profile=(TextView) findViewById(R.id.profile);

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


    public void bookfn()
    {
        SharedPreferences sf=getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences sf1=getSharedPreferences(preference1,Context.MODE_PRIVATE);
        x = sf.getString(saveit,"");
        y=sf1.getString(saveit1,"");
        if(x.equals("none selected")||y.equals("none selected")) {
            startActivity(new Intent(MainActivity.this, branch_year.class));
        }
        else
            startActivity(new Intent(MainActivity.this,recycle.class));

    }

    public void vdofn()
    {

        SharedPreferences sf=getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences sf1=getSharedPreferences(preference1,Context.MODE_PRIVATE);
        x = sf.getString(saveit,"");
        y=sf1.getString(saveit1,"");

            startActivity(new Intent(MainActivity.this,recycle.class));
    }
    public void profilefn()
    {
        startActivity(new Intent(MainActivity.this, Profile.class));
    }


}
