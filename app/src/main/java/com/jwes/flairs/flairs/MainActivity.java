package com.jwes.flairs.flairs;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class MainActivity extends AppCompatActivity {
    String check="";
    BottomBar nBottomBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nBottomBar=BottomBar.attach(this,savedInstanceState);
        nBottomBar.setItemsFromMenu(R.menu.menu_tabbed, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int i) {

                if(i==R.id.book)
                {
                    BookFragment f=new BookFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }

                if(i==R.id.video)
                {
                    VideoFragment f=new VideoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }

                if(i==R.id.library)
                {
                    LibraryFragment f=new LibraryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }

                if(i==R.id.user)
                {
                    ProfileFragment f=new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }


            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

        nBottomBar.mapColorForTab(0,"#00bcd4");
        nBottomBar.mapColorForTab(1,"#00bcd4");
        nBottomBar.mapColorForTab(2,"#00bcd4");
        nBottomBar.mapColorForTab(3,"#00bcd4");


    }
}
