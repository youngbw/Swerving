package com.youngdesigns.swerve;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import model.PostUI;
import model.SwervePost;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private static final String FIRST_STEP = "Next";
    private static final String SECOND_STEP = "Swerve!";


    private RadioButton mPictureButton;
    private RadioButton mTextButton;
    private Button mNextButton;
    private PostUI fragment;

    public PostFragment() {
        // Required empty public constructor
    }

    public static PostFragment newInstance() {
        PostFragment frag = new PostFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post, container, false);

        Fragment fragment = PictureCameraFragment.newInstance();
        this.fragment = (PostUI) fragment;
        getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, fragment).commit();

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();


        mPictureButton = (RadioButton) getActivity().findViewById(R.id.pictureRadioButton);
        mTextButton = (RadioButton) getActivity().findViewById(R.id.textRadioButton);
        mNextButton = (Button) getActivity().findViewById(R.id.nextButtonPost);

        mPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = PictureCameraFragment.newInstance();
                moveToFragment(frag);
            }
        });

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = PictureCameraFragment.newInstance();
                moveToFragment(frag);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveToNextStepSwerve();
            }
        });



    }

    private void moveToNextStepSwerve() {

        if (mNextButton.getText().toString().equals(FIRST_STEP)) {

            mNextButton.setText(SECOND_STEP);

            SwervePost post = this.fragment.composeSwerve();

            Fragment chooseFragment = ChoosePublishFragment.newInstance(post);

            moveToFragment(chooseFragment);


        } else if (mNextButton.getText().toString().equals(SECOND_STEP)) {

            mNextButton.setText(FIRST_STEP);

            //DEBUG SO WE CAN SEE LAST POST

            SwervePost post = this.fragment.composeSwerve();

            Log.d("SWERVE POST NULL?", "" + post);

            //END DEBUG
            this.fragment.postNewSwerve(post);

            Fragment fragment1 = PictureCameraFragment.newInstance();

            moveToFragment(fragment1);

        }

    }

    /**
     *
     * @param frag
     * @precondition passed fragment must implement PostUI interface.
     */
    private void moveToFragment(Fragment frag) {
        this.fragment = (PostUI) frag;
        getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, frag).commit();
    }
}
