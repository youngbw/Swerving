package com.youngdesigns.swerve;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
    private String fileName;
    private boolean pictureTaken;


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

        ((ImageButton) v.findViewById(R.id.take_picture_button)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m) {
                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        fileName = "thepicture" + ".jpeg";
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

                            pictureTaken = true;

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
        pictureTaken = false;
        FrameLayout preview = (FrameLayout) getActivity().findViewById(R.id.cameraSurface);
        preview.addView(mPreview, 0); //this causes crash occasionally, possibly when screen turns off

        final EditText locationView = (EditText) getActivity().findViewById(R.id.location_search);
        locationView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                locationView.clearComposingText();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("CHAR SEQUENCE LOC", s + "");
                String result = searchLocation(s);
//                locationView.append(result, 0, result.length());
//                locationView.setSelection(start + count - 1);
//                locationView.extendSelection(locationView.getText().toString().length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
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
    public void postNewSwerve(SwervePost post) {

        //TODO: database things to grab picture and put post and pic into db

    }

    @Override
    public SwervePost composeSwerve() {

        post.setPostedDate(new Date());

        SharedPreferences prefs = getActivity().getApplicationContext().getSharedPreferences(User.USER_PREFS, Context.MODE_PRIVATE);
        post.setUserID(prefs.getString(User.USER_ID, "-1"));


        EditText mCaption = (EditText)getActivity().findViewById(R.id.captionTextView);
        post.setCaption(mCaption.getText().toString());

        EditText location = (EditText)getActivity().findViewById(R.id.location_search);
        post.setLocation(location.getText().toString());

        CheckBox commentAllow = (CheckBox) getActivity().findViewById(R.id.allow_checkbox);
        post.setCommentsAllowed(commentAllow.isChecked());

//        //DEBUG
//        prefs.edit().putString("CAPTION", post.getCaption());
//        //END DEBUG

        return post;

    }

    public void hidePicture(boolean toHide) {
        FrameLayout pictureFrame = (FrameLayout) getActivity().findViewById(R.id.cameraSurface);
        if (toHide) {
            pictureFrame.setVisibility(View.GONE);
        } else {
            pictureFrame.setVisibility(View.VISIBLE);
        }
    }

    private String searchLocation(CharSequence sequence) {
        return sequence + "";
    }

}
