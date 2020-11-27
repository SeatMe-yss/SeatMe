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

public class Client_Activity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    Button made_reservation;
    Button menu;
    Button rank;
    Button myOrder;
    Button logout;
    Spinner spinner_rest;
    String[] rest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_activity);
        //find view by id
        made_reservation=findViewById(R.id.buttom1);
        menu=findViewById(R.id.button2);
        rank=findViewById(R.id.button3);
        myOrder=findViewById(R.id.ok);
        logout=findViewById(R.id.logout);

        //spinner
        spinner_rest=findViewById(R.id.branch_sppiner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_rest, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rest.setAdapter(adapter);
        spinner_rest.setOnItemSelectedListener(this);

        //set on clicklistener
        made_reservation.setOnClickListener(this);
        menu.setOnClickListener(this);
        rank.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        logout.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if(v==made_reservation){
            Intent intent= new Intent(this, order_place.class );
            startActivity(intent);
        }
        else if(v==logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(this, login_register.class );
            startActivity(intent);
            finish();

        }
        else if(v==menu){//just checking
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

//    public void logout() {
//
//    }
}