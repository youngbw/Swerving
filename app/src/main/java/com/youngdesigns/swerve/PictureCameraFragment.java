package com.youngdesigns.swerve;


import android.app.Fragment;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.youngdesigns.swerve.Camera.CameraPreview;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureCameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureCameraFragment extends Fragment {


    private Camera mCamera;
    private FrameLayout mSurfaceView;
    private CameraPreview mPreview;


    public static PictureCameraFragment newInstance() {
        PictureCameraFragment fragment = new PictureCameraFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        mSurfaceView = (FrameLayout) v.findViewById(R.id.cameraSurface);
        ((FrameLayout) v.findViewById(R.id.cameraSurface)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do camera things
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = getCameraInstance();
        mPreview = new CameraPreview(getActivity(), mCamera);
        FrameLayout preview = (FrameLayout) getActivity().findViewById(R.id.cameraSurface);
        preview.addView(mPreview);
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a com.youngdesigns.swerve.Camera instance
            List<Camera.Size> sizes = c.getParameters().getSupportedPreviewSizes();
            for (Camera.Size size : sizes) {
                Log.d("Supported Camera Sizes", size.width + " " + size.height);
            }

        } catch (Exception e) {
        }
        return c;
    }

    public PictureCameraFragment() {
        // Required empty public constructor
    }
}
