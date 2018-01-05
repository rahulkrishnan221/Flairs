package com.jwes.flairs.flairs;

import android.app.Activity;
import android.content.Intent;
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

public class Profile extends AppCompatActivity {

    private ImageButton logout;
    private FirebaseAuth authen;
    private LinearLayout depart;
    private LinearLayout cont;
    private LinearLayout develop;
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
        depart=(LinearLayout)findViewById(R.id.department);
        cont=(LinearLayout)findViewById(R.id.contact);
        develop=(LinearLayout)findViewById(R.id.developers);
        rating=(LinearLayout)findViewById(R.id.rate);

        }
    public void out()
    {
        authen.signOut();
        if(authen.getCurrentUser() ==null)
        {
            startActivity(new Intent(Profile.this,option.class));
        }
    }




}
