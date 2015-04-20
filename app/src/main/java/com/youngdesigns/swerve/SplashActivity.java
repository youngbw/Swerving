package com.youngdesigns.swerve;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import model.User;


public class SplashActivity extends ActionBarActivity {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
