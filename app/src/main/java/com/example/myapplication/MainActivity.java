package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button login;
    Button register;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
        login=(Button)findViewById(R.id.sign_in);
        register=(Button)findViewById(R.id.register1);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==login){
            Intent intent= new Intent(this,login.class );
            startActivity(intent);
        }
        if(v==register){
            Intent intent= new Intent(this,register.class) ;
            startActivity(intent);
        }
    }
}
