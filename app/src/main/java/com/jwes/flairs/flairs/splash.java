package com.jwes.flairs.flairs;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                // close this activity
                firebaseAuth = FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser()!=null) {
                    startActivity(new Intent(splash.this, MainActivity.class));
                }
                else
                {
                    Intent x=new Intent(splash.this,option.class);
                    startActivity(x);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}

