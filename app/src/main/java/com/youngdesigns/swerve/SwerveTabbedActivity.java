package com.youngdesigns.swerve;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import model.SwerveLab;


public class SwerveTabbedActivity extends ActionBarActivity {


    private FragmentManager manager;
    private FeedFragment mFeedFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swerve_tabbed);
        mFeedFragment = null;
        manager = getFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new FeedFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swerve_tabbed, menu);
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

    public void buttonClicked(View view) {
        Fragment fragment = null;
        if (view.getId() == R.id.feed_button) {

            if (mFeedFragment == null) fragment = FeedFragment.newInstance();
            else fragment = mFeedFragment;

        } else if (view.getId() == R.id.post_button) {

            fragment = new PostFragment();

        }

        if (fragment != null) {
            manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}
