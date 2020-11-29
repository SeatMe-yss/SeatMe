package com.example.myapplication.Activities;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class activity_rest extends AppCompatActivity {
    Button menu;
    Button review;
    Button diary;
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        //find view by id
        menu=findViewById(R.id.menu);
        review=findViewById(R.id.review);
        diary=findViewById(R.id.diary);
        change=findViewById(R.id.change);

    }


    }