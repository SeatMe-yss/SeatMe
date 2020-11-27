package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Client_Activity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    Button made_reservation;
    Button menu;
    Button rank;
    Button myOrder;
//    Button logout;
    Spinner spinner_rest;
    String[] rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_activity);
        made_reservation=(Button)findViewById(R.id.buttom1);
        made_reservation.setOnClickListener(this);
        menu=(Button)findViewById(R.id.button2);
        menu.setOnClickListener(this);
        rank=(Button)findViewById(R.id.button3);
        rank.setOnClickListener(this);
        myOrder=(Button)findViewById(R.id.ok);
        myOrder.setOnClickListener(this);
        spinner_rest=findViewById(R.id.branch_sppiner);
//        logout.findViewById(R.id.logout);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_rest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rest.setAdapter(adapter);
        spinner_rest.setOnItemSelectedListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v==made_reservation){
            Intent intent= new Intent(this, order_place.class );
            startActivity(intent);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    public void logout(View v) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(), login_register.class ));
//        finish();
//    }
}