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

public class Login extends AppCompatActivity {

    EditText emailID, password;
    Button btnSignup;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth =FirebaseAuth.getInstance();
        emailID=findViewById(R.id.editTextEmailSignup);
        password=findViewById(R.id.editTextPass1);
        btnSignup=findViewById(R.id.buttonSignup);
        tvSignIn=findViewById(R.id.textViewGoToSignInPage);
    }

    public void onClickSignup(View view) {
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
            Toast.makeText(Login.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
        }
        else if (!(email.isEmpty()&& pas.isEmpty())){
            mFirebaseAuth.createUserWithEmailAndPassword(email,pas).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(Login.this,"Sign up Unsuccessfull, try again",Toast.LENGTH_SHORT).show();
                    }
                    else {   //enter into MainActivity page
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }
                }
            });
        }
        else {
            Toast.makeText(Login.this,"Error Ocurred",Toast.LENGTH_SHORT).show();
        }

        emailID.setText("");
        password.setText("");
    }

    /**already have a account, use intent2 go to signin page activity. */
    public void goToSignInPage(View view) {
        Intent intent2=new Intent(Login.this,SignIntwoActivity.class);
        startActivity(intent2);
    }
}
