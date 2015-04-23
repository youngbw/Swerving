package com.youngdesigns.swerve;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private RadioButton mPictureButton;
    private RadioButton mTextButton;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post, container, false);

        Fragment fragment = PictureCameraFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, fragment).commit();

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();


        mPictureButton = (RadioButton) getActivity().findViewById(R.id.pictureRadioButton);
        mTextButton = (RadioButton) getActivity().findViewById(R.id.textRadioButton);

        mPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = PictureCameraFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, frag);
            }
        });

        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = PictureCameraFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.nested_post_fragment_container, frag);
            }
        });



    }
}
