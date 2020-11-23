package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.myapplication.R;

public class order_place extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String[] Branches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TimePicker picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}