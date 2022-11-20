package com.example.cloudtutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIntwoActivity extends AppCompatActivity {

    EditText emailID, password;
    Button btnSignin;
    TextView tvSignup;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_intwo);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editTextEmailSignin);
        password=findViewById(R.id.editTextPassSignin);
        btnSignin=findViewById(R.id.buttonSignInFireBaseAccount);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!=null){
                    Toast.makeText(SignIntwoActivity.this,"You are logged in ",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignIntwoActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(SignIntwoActivity.this,"please login",Toast.LENGTH_SHORT).show();
                }
            }
        };}

            public void onClick(View view) {
                String email=emailID.getText().toString();
                String pas=password.getText().toString();
                if(email.isEmpty()){
                    emailID.setError("please enter email");
                    emailID.requestFocus();
                }
                else if(pas.isEmpty()){
                    password.setError("please enter your password");
                    password.requestFocus();
                }
                else if (email.isEmpty()&& pas.isEmpty()){
                    Toast.makeText(SignIntwoActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty()&& pas.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pas).addOnCompleteListener(SignIntwoActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(SignIntwoActivity.this,"Login Error try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent intToHome=new Intent(SignIntwoActivity.this,MainActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignIntwoActivity.this,"Error Ocurred",Toast.LENGTH_SHORT).show();
                }
            }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
