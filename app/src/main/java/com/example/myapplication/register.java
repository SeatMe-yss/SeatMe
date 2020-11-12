package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity implements View.OnClickListener  {

    Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register=(Button)findViewById(R.id.login2);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==register){
            Intent intent= new Intent(this,activities.class );
            startActivity(intent);
        }
    }
}
