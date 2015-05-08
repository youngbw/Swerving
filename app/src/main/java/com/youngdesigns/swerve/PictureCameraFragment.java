package com.youngdesigns.swerve;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.youngdesigns.swerve.Camera.CameraPreview;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import model.PostUI;
import model.SwervePost;
import model.User;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PictureCameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PictureCameraFragment extends Fragment implements PostUI {


    private Camera mCamera;
    private FrameLayout mSurfaceView;
    private CameraPreview mPreview;
    private SwervePost post;


    public static PictureCameraFragment newInstance() {
        PictureCameraFragment fragment = new PictureCameraFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        post = new SwervePost();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_picture_camera, container, false);

        mSurfaceView = (FrameLayout) v.findViewById(R.id.cameraSurface);

        ((FrameLayout) v.findViewById(R.id.cameraSurface)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m) {
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        String fileName = "thepicture" + ".jpeg";
                        FileOutputStream os = null;
                        boolean success = true;

                        try {
                            //writes photo to local storage
                            os = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                            os.write(data);

                            //FOR DEBUG BEFORE SERVICES
                            SharedPreferences prefs = getActivity().getApplication().getSharedPreferences(User.USER_PREFS, Context.MODE_PRIVATE);
                            prefs.edit().putString("PATH", fileName).commit();
                            //END DEBUG

                            //add path to swerve post
                            post.setImagePath(fileName);


                        } catch (Exception e) {
                            Log.e("FILE ERROR DUDE", "" + e.toString());
                            e.printStackTrace();
                            success = false;
                        } finally {

                            try {
                                if (os != null) os.close();

                            } catch (Exception e) {
                                e.printStackTrace();
                                success = false;
                            }
                        }
                        Toast.makeText(getActivity().getApplicationContext(), "WAS SUCCESSFUL? " + success, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity().getApplicationContext(), "PIC TAKEN", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
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
        preview.addView(mPreview); //this causes crash occasionally, possibly when screen turns off
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

    @Override
    public void onPause() {
        if (mCamera != null) mCamera.release();
        super.onPause();
    }

    public PictureCameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void postNewSwerve(SwervePost post) {}

    @Override
    public SwervePost composeSwerve() {

        post.setPostedDate(new Date());

        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(User.USER_PREFS, Context.MODE_PRIVATE);
        post.setUserID(prefs.getString(User.USER_ID, "-1"));


        EditText mCaption = (EditText)getActivity().findViewById(R.id.captionTextView);
        post.setCaption(mCaption.getText().toString());

        Toast.makeText(getActivity().getApplicationContext(), mCaption.getText().toString() , Toast.LENGTH_SHORT).show();

        //DEBUG
        prefs.edit().putString("CAPTION", post.getCaption());
        //END DEBUG

        return post;

    }
}
