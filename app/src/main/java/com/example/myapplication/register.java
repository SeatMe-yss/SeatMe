package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button register;
    Spinner spinner_type;
    String[] types;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register=(Button)findViewById(R.id.login2);
        register.setOnClickListener(this);
        spinner_type=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        spinner_type.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==register){
            Intent intent= new Intent(this, Client_Activity.class );
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
}
