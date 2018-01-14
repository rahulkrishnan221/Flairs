package com.jwes.flairs.flairs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

import static com.jwes.flairs.flairs.R.drawable.department;
import static com.jwes.flairs.flairs.branch_year.preference;
import static com.jwes.flairs.flairs.branch_year.preference1;
import static com.jwes.flairs.flairs.branch_year.saveit;
import static com.jwes.flairs.flairs.branch_year.saveit1;

public class Profile extends AppCompatActivity {

    private ImageButton logout;
    private FirebaseAuth authen;
    private LinearLayout branch_year_button;
    private LinearLayout cont;
    private LinearLayout rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View view = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=(ImageButton) findViewById(R.id.logout);
        authen =FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out();
            }
        });
        branch_year_button=(LinearLayout)findViewById(R.id.branch_year_button);
        cont=(LinearLayout)findViewById(R.id.contact);
        rating=(LinearLayout)findViewById(R.id.rate);

        branch_year_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,branch_year.class));

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact();
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate();
            }
        });

        }
    public void out()
    {
        authen.signOut();
        if(authen.getCurrentUser() ==null)
        {
            SharedPreferences sf=getSharedPreferences(preference, Context.MODE_PRIVATE);
            SharedPreferences sf1=getSharedPreferences(preference1,Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sf.edit();
            SharedPreferences.Editor editor1=sf1.edit();

            editor.putString(saveit,"none selected" );
            editor1.putString(saveit1,"none selected");
            editor.commit();
            editor1.commit();

            startActivity(new Intent(Profile.this,option.class));

        }
    }

    public void contact()
    {
            startActivity(new Intent(Profile.this,contact.class));

    }

    public void rate()
    {
            startActivity(new Intent(Profile.this,rate.class));

    }

}
