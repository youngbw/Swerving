package com.youngdesigns.swerve;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import model.PostUI;
import model.SwervePost;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostActivity extends Activity {

    private static final String FIRST_STEP = "Next";
    private static final String SECOND_STEP = "Swerve!";


    private RadioButton mPictureButton;
    private RadioButton mTextButton;
    private Button mNextButton;
    private PostUI fragment;

    public PostActivity() {
        // Required empty public constructor
    }

//    public static PostFragment newInstance() {
//        PostFragment frag = new PostFragment();
//        Bundle args = new Bundle();
//        frag.setArguments(args);
//        return frag;
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_post, container, false);
//
//        Fragment fragment = PictureCameraFragment.newInstance();
//        this.fragment = (PostUI) fragment;
//        getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, fragment).commit();
//
//        return v;
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Fragment fragment = PictureCameraFragment.newInstance();
        this.fragment = (PostUI) fragment;
        getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, fragment).commit();
    }

    @Override
    public void onResume() {
        super.onResume();


        mPictureButton = (RadioButton) findViewById(R.id.pictureRadioButton);
        mTextButton = (RadioButton) findViewById(R.id.textRadioButton);
        mNextButton = (Button) findViewById(R.id.nextButtonPost);

        mPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof PictureCameraFragment) ((PictureCameraFragment)fragment).hidePicture(false);
            }
        });

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof PictureCameraFragment) ((PictureCameraFragment)fragment).hidePicture(true);
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

        SwervePost post = fragment.composeSwerve();
        fragment.postNewSwerve(post);

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
