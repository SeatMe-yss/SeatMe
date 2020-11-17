package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Client_Activity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    Button made_reservation;
    Button menu;
    Button rank;
    Button myOrder;
    Spinner spinner_rest;
    String[] rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_activity);
        made_reservation=(Button)findViewById(R.id.button1);
        made_reservation.setOnClickListener(this);
        menu=(Button)findViewById(R.id.button2);
        menu.setOnClickListener(this);
        rank=(Button)findViewById(R.id.button3);
        rank.setOnClickListener(this);
        myOrder=(Button)findViewById(R.id.button4);
        myOrder.setOnClickListener(this);
        spinner_rest=findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_rest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rest.setAdapter(adapter);
        spinner_rest.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}