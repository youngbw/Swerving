package com.youngdesigns.swerve;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import model.SwervePost;


public class CommentActivity extends ActionBarActivity {

    private String mPostID;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        manager = getFragmentManager();

        if (savedInstanceState == null) {

            Fragment fragment = manager.findFragmentById(R.id.comment_fragment_container);

            if (fragment == null) {
                getIntent().getStringExtra(SwervePost.POST_ID);
                fragment = FeedListFragment.newInstance(mPostID, FeedListFragment.COMMENTS);
            }
            manager.beginTransaction().add(R.id.comment_fragment_container, fragment).commit();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_comment, menu);
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
