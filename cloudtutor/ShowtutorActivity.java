package com.example.cloudtutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.cloudtutor.dummy.DummyContent;

public class ShowtutorActivity extends AppCompatActivity implements TutorlistfragFragment.OnListFragmentInteractionListener, Tutordisplayfrag.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtutor);

        //get intent from mainactivity by key: SECRET_MESSAGE_STUDENT, when show tutor button is clicked.
        //when button SHOW TUTOR is clicked, from MainActivity move to this ShowtutorActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.INTENT_KEY_SHOW_TUTORS);
    }

    /**override interface method from the TutorListfragFragment.java:
     * to communicate list selected item to second fragment's textOutput.
     * when a tutor item is selected in the top list fragment, this method is called.
     * DummyContent Class object's attribute DummyItem is passed in as the parameter. */
    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        /**Below to get the info display fragment by FragmentManaer into the listfragment's interface method. */
        FragmentManager fragmentManager = getSupportFragmentManager();
        Tutordisplayfrag fragment = (Tutordisplayfrag) fragmentManager.findFragmentById(R.id.fragmenDisplayOnShowtutorActivityID);

        Log.i("taglabelA", "this is from log.i when select an item from list.Inside of override onListFragmentInteraction method.  " );
        /**pass in item.details from first fragment's menu list element(dummyItem)'s details attribute variable. */
        if(fragment!=null)
            fragment.setOutputText(item.name,item.classes, item.details);
        //display the list item detail into outputTextView, pass the parameter's string value to textViews

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
