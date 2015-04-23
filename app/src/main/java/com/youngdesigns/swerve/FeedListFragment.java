package com.youngdesigns.swerve;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import model.SwerveLab;
import model.SwervePost;

/**
 * Created by BrentYoung on 4/21/15.
 */
public class FeedListFragment extends android.app.ListFragment {

    private ArrayList<SwervePost> mSwerves;
    private SwerveAdapter adapter;

    public static FeedListFragment newInstance() {
        FeedListFragment frag = new FeedListFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.title_activity_swerve_tabbed);
        mSwerves = SwerveLab.getInstance(getActivity()).getSwerves();

        adapter = new SwerveAdapter(mSwerves);
        setListAdapter(adapter);
        getListView().setDividerHeight(20);

    }



    private class SwerveAdapter extends ArrayAdapter<SwervePost> {


        public SwerveAdapter(ArrayList<SwervePost> swerves) {
            super(getActivity(), 0, swerves);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            return super.getView(position, convertView, parent);

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_feed_item_list, null);
            }

            SwervePost sp = getItem(position);

            ImageView image = (ImageView) convertView.findViewById(R.id.listPictureView);
            if (sp.getImageURI() != null) {
                image.setImageURI(sp.getImageURI());
            } else {
                image.setVisibility(View.GONE);
            }

            TextView text = (TextView) convertView.findViewById(R.id.listTextView);
            text.setText(sp.getCaption());

            ProgressBar bar = (ProgressBar) convertView.findViewById(R.id.listProgressBar);
            bar.setMax(100);
            bar.setProgress((int)sp.getSwervePercentage());

            Button comment = (Button) convertView.findViewById(R.id.listCommentButton);
            if (!sp.getComments().isEmpty()) comment.setTextColor(Color.BLUE);

            TextView date = (TextView) convertView.findViewById(R.id.listTextDateView);
            date.setText(sp.getPostedDate().toString());

            return convertView;

        }
    }

}
