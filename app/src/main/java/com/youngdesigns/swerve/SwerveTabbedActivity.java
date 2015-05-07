package com.youngdesigns.swerve;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class SwerveTabbedActivity extends ActionBarActivity {


    private FragmentManager manager;

    //Trying to use this to save which fragment app navigates to with action bar back button, phone back button works properly
    private Fragment mFeedFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swerve_tabbed);
        manager = getFragmentManager();

        if (savedInstanceState == null) {

            Fragment fragment = manager.findFragmentById(R.id.fragment_container);

            if (fragment == null) {
                fragment = FeedFragment.newInstance();
            }
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

            fragment = FeedFragment.newInstance();

        } else if (view.getId() == R.id.post_button) {

            fragment = PostFragment.newInstance();

        } else if (view.getId() == R.id.friendsTabButton) {

            fragment = FeedListFragment.newInstance(FeedListFragment.FRIENDS);

        } else if (view.getId() == R.id.accountGroupsButton) {

            fragment = FeedListFragment.newInstance(FeedListFragment.GROUPS);

        }

        if (fragment != null) {
//            manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            mFeedFragment = fragment;
            manager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


}
