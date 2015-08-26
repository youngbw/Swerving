package com.youngdesigns.swerve;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import model.Comment;
import model.Group;
import model.SwerveLab;
import model.SwervePost;
import model.SwipeDetector;
import model.User;

/**
 * Created by BrentYoung on 4/21/15.
 */
public class FeedListFragment extends android.app.ListFragment {

    public static final int SWERVES = 0;
    public static final int FRIENDS = 1;
    public static final int GROUPS = 2;
    public static final int COMMENTS = 3;

    private static final String TAG = "com.youngdesigns.swerve.LIST_TYPE";
    private static final int DIVIDER_HEIGHT = 20;

    private ArrayList<SwervePost> mSwerves;
    private ArrayList<User> mFriends;
    private ArrayList<Group> mGroups;
    private ArrayList<Comment> mComments;
    private ListAdapter adapter;
    private int type;
    private String postID;

    public static FeedListFragment newInstance(int theType) {
        FeedListFragment frag = new FeedListFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, theType);
        frag.setArguments(args);
        return frag;
    }

    /**
     * Overloaded constructor to provide functionality for post-specific listing. i.e. comments
     * @param postID ID of the post to be worked with
     * @param theType the static final int type that defines the adapter for this list, comments in this case to take advantage of the postID.
     * @return a new FeedListFragment Instance
     */
    public static FeedListFragment newInstance(String postID, int theType) {
        FeedListFragment frag = new FeedListFragment();
        Bundle args = new Bundle();
        args.putInt(TAG, theType);
        args.putString(SwervePost.POST_ID, postID);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.type = args.getInt(TAG, SWERVES);
            this.postID = args.getString(SwervePost.POST_ID, "");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.app_name);

        if (type == SWERVES) {
            mSwerves = SwerveLab.getInstance(getActivity()).getSwerves();

            //DEBUG
//            SharedPreferences prefs = getActivity().getApplication().getSharedPreferences(User.USER_PREFS, Context.MODE_PRIVATE);
//            SwervePost debugPost = new SwervePost();
//            debugPost.setImagePath(prefs.getString("PATH", "thepicture.jpg"));
//            debugPost.setCaption(prefs.getString("CAPTION", "woops there wasnt one!"));
//
//            mSwerves.add(debugPost);
            //END DEBUG

            adapter = new SwerveAdapter(mSwerves);
            getListView().setDividerHeight(DIVIDER_HEIGHT);

        } else if (type == FRIENDS) {

            mFriends = SwerveLab.getInstance(getActivity()).getFriends();
            mFriends.add(new User());
            mFriends.add(new User());
            User me = new User();
            me.setName("Brent");
            mFriends.add(me);
            adapter = new FriendsAdapter(mFriends);

        } else if (type == GROUPS) {

            mGroups = SwerveLab.getInstance(getActivity()).getGroups();
            adapter = new GroupsAdapter(mGroups);

        } else if (type == COMMENTS) {

            mComments = SwerveLab.getInstance(getActivity()).getComments(postID); // query comments using postID
            adapter = new CommentsAdapter(mComments);

            getListView().setDividerHeight(3);

        }
        setListAdapter(adapter);



    }



    private class SwerveAdapter extends ArrayAdapter<SwervePost> {


        public SwerveAdapter(ArrayList<SwervePost> swerves) {
            super(getActivity(), 0, swerves);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            return super.getView(position, convertView, parent);
//            convertView = super.getView(position, convertView, parent);
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_feed_item_list, null);
            }

            final SwervePost sp = getItem(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.listPictureView);

            //TODO: modify this if statement to work with the returned database version of Swerve Pic
            if (true) {//sp.getImagePath() != null) {
                BitmapDrawable b = PictureUtils.getScaledDrawable(getActivity(), sp.getImagePath());
                image.setImageDrawable(b);
                image.setVisibility(View.VISIBLE);
            } else {
                image.setVisibility(View.GONE);
            }

            TextView text = (TextView) convertView.findViewById(R.id.listTextView);
            text.setText(sp.getCaption());

            final TextView counter = (TextView) convertView.findViewById(R.id.swerve_count_text);
            counter.setText("SS " + sp.getSwerveCount());
            counter.setTextColor(Color.CYAN);

            Button comment = (Button) convertView.findViewById(R.id.listCommentButton);
            if (!sp.getComments().isEmpty()) {
                comment.setTextColor(Color.CYAN);
                comment.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(getActivity(), CommentActivity.class);
                        myIntent.putExtra(SwervePost.POST_ID, sp.getId());
                        getActivity().startActivity(myIntent);
                    }
                });
            }


            TextView date = (TextView) convertView.findViewById(R.id.listTextDateView);
            DateFormat format = DateFormat.getDateTimeInstance();
            date.setText(format.format(sp.getPostedDate()));

            convertView.setOnTouchListener(new SwipeDetector(counter, sp));
            return convertView;

        }
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

    private class GroupsAdapter extends ArrayAdapter<Group> {


        public GroupsAdapter(ArrayList<Group> groups) {
            super(getActivity(), 0, groups);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            return super.getView(position, convertView, parent);

            //TODO:change the fragment_friends_list_item to groups list item once created
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_friends_list_item, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.friendsListNameText);
            name.setText(getItem(position).toString());


            return convertView;

        }
    }

    private class CommentsAdapter extends ArrayAdapter<Comment> {

        public CommentsAdapter(ArrayList<Comment> comments) {
            super(getActivity(), 0, comments);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_comments_list_item, null);
            }


            TextView nameView = (TextView) convertView.findViewById(R.id.user_comment_tag);
            nameView.setText(getItem(position).getUser());
            //do view things, grab comments from post and associate with userID

            TextView commentView = (TextView) convertView.findViewById(R.id.comment_message_view);
            commentView.setText(getItem(position).getComment());

            return convertView;

        }


    }
}
