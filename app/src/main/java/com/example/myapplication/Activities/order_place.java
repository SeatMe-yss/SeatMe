package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class order_place extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText order_meal;
    DatePicker date;
    Spinner time;
    Button order;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);

        //view by id
        order_meal=findViewById(R.id.order_meal);
        date=findViewById(R.id.datePicker1);
        time=findViewById(R.id.spinnertime);
        order=findViewById(R.id.order_place1);


        //firebase
        fAuth= FirebaseAuth.getInstance();

        //spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter);
        time.setOnItemSelectedListener(this);



        order.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get all the value
                String order_message=order_meal.getText().toString();
                String time_order=time.getSelectedItem().toString();
                String month=date.getMonth()+"";
                String year=date.getYear()+"";
                String day=date.getDayOfMonth()+"";
                String Rest_name=""; //need to get from the before activity
                //get an new key
                Order O= new Order(Rest_name, order_message, time_order, year, month, day );
                O.setOrder_id(DB_model.get_DB().push().getKey()); //inialize key




            }
        });



        }

    @Override
    public void onClick(View v) {



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DatePicker date=findViewById(R.id.datePicker1);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}