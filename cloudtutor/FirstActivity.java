package com.example.cloudtutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button buttonr;
    Button buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        buttonr=findViewById(R.id.button);
        buttons=findViewById(R.id.button2);

    }
    public void onClick(View view){
        Intent i2=new Intent(FirstActivity.this,SignIntwoActivity.class);
        startActivity(i2);
    }

    public void onClickk(View view){
        Intent i11=new Intent(FirstActivity.this,Login.class);
        startActivity(i11);
    }
}
