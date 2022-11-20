package com.example.cloudtutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.cloudtutor.dummy.DummyContent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private EditText tutorNameEditText;
    private EditText tutorClassEditText;
    private EditText tutorProfileEditText;

    private static final String TAG = "EmailPassword";
    private static final String TAGSEETUTOR ="inside see tutor button";
    public static final String ADDTUTOR_TO_FIREBASE = "ADD_TUTOR_TO_FIREBASE_TUTORS_START_COLLECTION";
    public static final String INTENT_KEY_SHOW_TUTORS = "FROM_MAINACTIVITY_TO_SHOWTUTORACTIVITY_INTENT_KEY";


    //access a cloud firestroe instance(object) from my Activity to use firebase database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //when show button is clicked, read tutors document from firebase, and add the data set to tutorSet.
    Map<String, Object> tutorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //views
        tutorNameEditText = findViewById(R.id.tutorNameEditText);
        tutorClassEditText = findViewById(R.id.tutorClassEditText);
        tutorProfileEditText = findViewById(R.id.tutorProfileEditText);

    }


    public void sendTutorsToFireBase(View view) {
        //sample codes from firebase - cloud firestore: create a new tutor with a first and last name
        Map<String, Object> tutor = new HashMap<>();
        tutor.put("name", tutorNameEditText.getText().toString());
        tutor.put("class",tutorClassEditText.getText().toString() );
        tutor.put("profile", tutorProfileEditText.getText().toString());

        //add a new document with a generated ID
        db.collection("tutors")
                .add(tutor)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID:" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intentLogout=new Intent(MainActivity.this,FirstActivity.class);
        startActivity(intentLogout);
    }


    public void seeTutors(View view) {

          //read data to retrieve the entire collection
        db.collection("tutors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            //clear list's items so that the button multiple click won't duplicate the list items
                            DummyContent.clearList();
                            //iterary (for each) get content
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d(TAGSEETUTOR, document.getId() +" => " + document.getData());
                                  //document.getData() is all the data retrieved from firebase
                                tutorSet = document.getData() ;  //get set {name:"", class="", profile=""}
                                String xName = (String) tutorSet.get("name");
                                String xClass = (String) tutorSet.get("class");
                                String xDetails = (String) tutorSet.get("profile");

                                //add the above strings to a new dummyContent.DummyItem object for each iterary
                                DummyContent.DummyItem aNewTutor = new DummyContent.DummyItem(xName,xClass,xDetails);
                                DummyContent.addItem(aNewTutor);    //add the above dummyItem into arrylist and map in DummyConten.java
                            }
                        } else {
                            Log.w(TAGSEETUTOR, "Error getting documents. ", task.getException());
                        }

                        //第一次读没问题，让后每次读会加在list的上一次读的数据之上
                    }
                });

        //move to ShowtutorActivity from MainActivity by intent
        Intent intent = new Intent(this, ShowtutorActivity.class);
        intent.putExtra(INTENT_KEY_SHOW_TUTORS,"whatever string message you want to sent to ShowtutorActivity");
        startActivity(intent);
    }



}
