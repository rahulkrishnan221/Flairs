package com.jwes.flairs.flairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class option extends AppCompatActivity{
    Button tologin;
    Button toregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        tologin=(Button)findViewById(R.id.tologin);

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(option.this,login.class);
                startActivity(i);
            }
        });
        toregister=(Button)findViewById(R.id.toregister);
        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(option.this,registerf.class);
                startActivity(j);
            }
        });
    }
}
