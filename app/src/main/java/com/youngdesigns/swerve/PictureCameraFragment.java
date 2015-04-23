package com.youngdesigns.swerve;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureCameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureCameraFragment extends Fragment {


    private Camera mCamera;
    private SurfaceView mSurfaceView;



    public static PictureCameraFragment newInstance() {
        PictureCameraFragment fragment = new PictureCameraFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public PictureCameraFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_picture_camera, container, false);

        mSurfaceView = (SurfaceView) v.findViewById(R.id.cameraSurface);
        ((SurfaceView) v.findViewById(R.id.cameraSurface)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do camera things
            }
        });

        return v;
    }

    @TargetApi(9)
    @Override
    public void onResume() {
        super.onResume();
    }
}
