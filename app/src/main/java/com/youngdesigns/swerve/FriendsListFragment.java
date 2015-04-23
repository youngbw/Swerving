package com.youngdesigns.swerve;


import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.User;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsListFragment extends ListFragment {

    private ArrayList<User> mFriends;
    private FriendsAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FriendsListFragment.
     */
    public static FriendsListFragment newInstance() {
        FriendsListFragment fragment = new FriendsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mFriends = some sort of database fetch;
        mFriends = new ArrayList<>();
        mFriends.add(new User());
        mFriends.add(new User());
        User me = new User();
        me.setName("Brent");
        mFriends.add(me);
        adapter = new FriendsAdapter(mFriends);
        setListAdapter(adapter);
    }


    private class FriendsAdapter extends ArrayAdapter<User> {


        public FriendsAdapter(ArrayList<User> friends) {
            super(getActivity(), 0, friends);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            return super.getView(position, convertView, parent);

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_friends_list_item, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.friendsListNameText);
            name.setText(getItem(position).getName());


            return convertView;

        }
    }

}
