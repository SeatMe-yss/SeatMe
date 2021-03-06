package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class login_register extends AppCompatActivity implements View.OnClickListener{
    Button login;
    Button register;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
       //view by id
        login=findViewById(R.id.sign_in);
        register=findViewById(R.id.register1);
        //setOnClickListener
        login.setOnClickListener(this);
        register.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        if(v==login){
            Intent intent= new Intent(this,login.class );
            startActivity(intent);
        }
        else if(v==register){
            Intent intent= new Intent(this,register.class) ;
            startActivity(intent);
        }
    }
}
