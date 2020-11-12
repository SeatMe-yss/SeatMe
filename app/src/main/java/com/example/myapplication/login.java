package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity implements View.OnClickListener {

    Button sign_in;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sign_in=(Button)findViewById(R.id.login2);
        sign_in.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==sign_in){
            Intent intent= new Intent(this,activities.class );
            startActivity(intent);
        }

    }
}
