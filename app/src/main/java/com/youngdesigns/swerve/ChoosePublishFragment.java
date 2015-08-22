package com.youngdesigns.swerve;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import model.PostUI;
import model.SwervePost;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChoosePublishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoosePublishFragment extends Fragment implements PostUI {

    private static final String SWERVE_TAG = "swerve_tag";

    private SwervePost post;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param post the SwervePost to publish
     * @return A new instance of fragment ChoosePublishFragment.
     */
    public static ChoosePublishFragment newInstance(SwervePost post) {
        ChoosePublishFragment fragment = new ChoosePublishFragment();
        Bundle args = new Bundle();
        args.putSerializable(SWERVE_TAG, post);
        fragment.setPost(post);
        fragment.setArguments(args);
        return fragment;
    }

    public ChoosePublishFragment() {
        // Required empty public constructor
    }

    public void setPost(SwervePost thePost) {
        this.post = thePost;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Toast.makeText(getActivity().getApplicationContext(), "IN ON CREATE FOR CHOOSE", Toast.LENGTH_SHORT).show();
            this.post = (SwervePost) getArguments().getSerializable(SWERVE_TAG);
//            this.post.makePublic(true); // TAKE THIS OUT AFTER DEBUG
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_publish, container, false);
    }

    @Override
    public void postNewSwerve(SwervePost post) {
        //do posty things here

    }

    @Override
    public SwervePost composeSwerve() {
        //add permissions and such in listeners for widgets
        return post;
    }


//    TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
