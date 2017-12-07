package com.jwes.flairs.flairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    private ImageButton logout;
    private FirebaseAuth authen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
