package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class activity_rest extends AppCompatActivity implements View.OnClickListener {
    Button menu;
    Button review;
    Button diary;
    Button change;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        //find view by id
        menu=findViewById(R.id.my_menu);
        review=findViewById(R.id.my_review);
        diary=findViewById(R.id.my_diary);
        change=findViewById(R.id.my_change);
        logout=findViewById(R.id.my_logout);

        //set on clicklistener
        menu.setOnClickListener(this);
        review.setOnClickListener(this);
        diary.setOnClickListener(this);
        change.setOnClickListener(this);
        logout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v==logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(this, login_register.class );
            startActivity(intent);
            finish();
        }
        else if(v==menu){

        }
        else if(v==review){

        }
        else if(v==diary){

        }
        else if(v==change){

            Intent intent= new Intent(this, change_my_rest.class );
            startActivity(intent);

        }

    }
}