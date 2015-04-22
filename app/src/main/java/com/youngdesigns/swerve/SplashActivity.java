package com.youngdesigns.swerve;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import model.User;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread myThread = new Thread() {

            @Override
            public void run() {

                try {
                    sleep(3000);
                    checkScreen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }

            }


        };

        myThread.start();

    }


    private void checkScreen() {

        Intent myIntent;
        SharedPreferences prefs = getSharedPreferences(User.USER_PREFS, MODE_PRIVATE);
//        if (prefs != null && prefs.contains(User.USER_ID)) {
//            myIntent = new Intent(SplashActivity.this, SwerveTabbedActivity.class);
//        } else {
//            myIntent = new Intent(SplashActivity.this, LoginActivity.class);
//        }

        //Placeholder for before functionality is implemented
        myIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(myIntent);

    }
}
