package com.jwes.flairs.flairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout vdo;
    private LinearLayout book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book=(LinearLayout)findViewById(R.id.temp);
        vdo=(LinearLayout)findViewById(R.id.temp1);

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

        startActivity(new Intent(MainActivity.this,branch_year.class));

    }

    public void vdofn()
    {

        startActivity(new Intent(MainActivity.this,recycle.class));

    }
}
