package com.youngdesigns.swerve;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import model.SwerveLab;
import model.SwervePost;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        FeedFragment frag = new FeedFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_feed, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        Fragment fragment = getFragmentManager().findFragmentById(R.id.nested_Feed_Fragment_container);

        fragment = FeedListFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.nested_Feed_Fragment_container, fragment).commit();

    }
}
