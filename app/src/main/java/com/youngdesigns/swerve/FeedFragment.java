package com.youngdesigns.swerve;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private Button mPublicButton;
    private Button mLocationButton;
    private Button mFriendsButton;
    private Button mGroupsButton;
    private Button mMySwervesButton;

    private SearchView search;
    private Spinner spinner;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        FeedFragment frag = new FeedFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
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

//        if (mPublicButton == null) {
//            mPublicButton = (Button) getActivity().findViewById(R.id.publicButton);
//            mPublicButton.setSelected(true);
//            //Load list with public list items here because this means this is the first time this activity has been created.
//        }
//        if (mLocationButton == null) mLocationButton = (Button) getActivity().findViewById(R.id.locationButton);
//        if (mFriendsButton == null) mFriendsButton = (Button) getActivity().findViewById(R.id.friendsButton);
//        if (mGroupsButton == null) mGroupsButton = (Button) getActivity().findViewById(R.id.groupButton);
//        if (mMySwervesButton == null) mMySwervesButton = (Button) getActivity().findViewById(R.id.mySwervesButton);

//        addListeners();

//        search = (SearchView) getActivity().findViewById(R.id.feedSearchView);
//        spinner = (Spinner) getActivity().findViewById(R.id.feedSpinner);

        Fragment fragment = FeedListFragment.newInstance(FeedListFragment.SWERVES); //may want to pass the list in here as an argument
        getFragmentManager().beginTransaction().replace(R.id.nested_Feed_Fragment_container, fragment).addToBackStack(null).commit();


    }

    public void addListeners() {

        mPublicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the list with public list stuff, start new feedlist
                hideAllExtras();
            }
        });

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the list with location list stuff, start new feedlist
                showSearchView();
            }
        });

        mFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the list with friends list stuff, start new feedlist
                showSearchView();
            }
        });

        mGroupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //load the list with groups list stuff, start new feedlist
                showSpinner();
            }
        });

        mMySwervesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //load list with users swerves
                hideAllExtras();
            }
        });

    }

    private void showSpinner() {
        if (search.getVisibility() == View.VISIBLE) {
            search.setVisibility(View.GONE);
        }
        spinner.setVisibility(View.VISIBLE);
    }

    private void showSearchView() {
        if (spinner.getVisibility() == View.VISIBLE) {
            spinner.setVisibility(View.GONE);
        }
        search.setVisibility(View.VISIBLE);
    }

    private void hideAllExtras() {
        search.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
    }
}
