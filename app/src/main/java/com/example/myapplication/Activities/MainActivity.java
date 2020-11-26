package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Button logo;
    ImageButton logo;
//    Button register;
//    TextView welcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        logo=findViewById(R.id.imageButton2);
        //register=(Button)findViewById(R.id.register1);
        logo.setOnClickListener(this);
        //register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this,login_register.class );
        startActivity(intent);

    }
}