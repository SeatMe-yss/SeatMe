package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myapplication.DB.DB_Business;
import com.example.myapplication.DB.DB_Orders;
import com.example.myapplication.DB.DB_model;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class order_place extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText order_meal;
    DatePicker date;
    Spinner time;
    Button order;
    FirebaseAuth fAuth;
    SharedPreferences sp;

    //
    String id_Bus ,  id_client;

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

        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);


        order.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get all the value
                String order_message=order_meal.getText().toString();
                String time_order=time.getSelectedItem().toString();
                String month=date.getMonth()+"";
                String year=date.getYear()+"";
                String day=date.getDayOfMonth()+"";

                //getting restaurant name from the last activity
                String Rest_name = sp.getString("restaurant name", "");

                //getting the id of rest
                DB_model.get_DB().getRef().child("Business").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String a=ds.child("name_rest").getValue(String.class);
                            System.out.println(a);
                            if(ds.child("name_rest").getValue(String.class).equals(Rest_name))
                                id_Bus =ds.child("id").getValue(String.class);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                //id client
                id_client=fAuth.getUid().toString();

                //get an new key
                Order  O= new  Order( Rest_name, order_message,  id_client, id_Bus,  time_order, year, month,  day);
                String Uid=DB_model.get_DB().child("Orders").push().getKey();//maybe .child("orders")
                O.setOrder_id(Uid);

                //add the order to DB_
               DB_Orders.addOrderToDB(O);

               //add order to Business
                Map<String,Object> mapB=new HashMap<>();
                mapB.put("Orders", O);
                DB_model.get_DB().getRef().child("Business").child(id_Bus).updateChildren(mapB);

                //add order to client
                Map<String,Object> mapC=new HashMap<>();
                mapC.put("Orders", O);
                DB_model.get_DB().getRef().child("Clients").child(id_client).updateChildren(mapC);


                startActivity(new Intent(getApplicationContext(),Client_Activity.class));









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