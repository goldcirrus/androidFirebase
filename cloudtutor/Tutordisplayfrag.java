package com.example.cloudtutor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tutordisplayfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tutordisplayfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tutordisplayfrag extends Fragment {

    private static TextView outputTutordetailText;
    private static TextView outputTutorNameText;
    private static TextView outputTutorCourseText;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tutordisplayfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tutordisplayfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static Tutordisplayfrag newInstance(String param1, String param2) {
        Tutordisplayfrag fragment = new Tutordisplayfrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**find textView objects on tutordisplayfrag.xml*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the fragment_details layout for this fragment java file.
        View view = inflater.inflate(R.layout.fragment_tutordisplayfrag, container, false);
        //view refer to fragment_tutordisplayfrag.xml
        outputTutordetailText = view.findViewById(R.id.textViewTutorProfileOut);
        outputTutorNameText = view.findViewById(R.id.textViewTutorNameOut);
        outputTutorCourseText = view.findViewById(R.id.textViewTutorClassesOut);

        //set scroll property ture, and add the belowing codes
        outputTutordetailText.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

    /**Method to be called in Showtutor.java to pass in string content from DummyContent's list. */
    public void setOutputText(String name, String course, String detail){
        outputTutorNameText.setText(name);
        outputTutorCourseText.setText(course);
        outputTutordetailText.setText(detail);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
