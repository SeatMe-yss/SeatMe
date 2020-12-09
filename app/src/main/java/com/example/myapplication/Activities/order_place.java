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
import android.widget.Toast;
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
    EditText num_people;
    DatePicker date;
    Spinner time;
    Button order;
    FirebaseAuth fAuth;
    SharedPreferences sp;

    //
    String id_Bus ,  id_client; int max_people_r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);

        //view by id
        order_meal=findViewById(R.id.order_meal);
        num_people=findViewById(R.id.order_meal2);
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


        String Rest_name = sp.getString("restaurant name", "");

        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String a = ds.child("name_rest").getValue(String.class);
                    if (ds.child("name_rest").getValue(String.class).equals(Rest_name)) {
                        id_Bus = ds.child("id").getValue(String.class);
                        max_people_r=ds.child("max_people").getValue(Integer.class); //getting the max people in res

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        order.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get all the value
                int numP=Integer.parseInt(num_people.getText().toString());
                String order_message=order_meal.getText().toString();
                String time_order=time.getSelectedItem().toString();
                String month=(date.getMonth()+1)+"";
                String year=date.getYear()+"";
                String day=date.getDayOfMonth()+"";

                //getting restaurant name from the last activity
                String Rest_name = sp.getString("restaurant name", "");

                gettingid();
                System.out.println("u:"+id_client+", c" +id_Bus);

                //id client
                id_client=fAuth.getUid().toString();


                //get an new key
                Order  O= new  Order( Rest_name, order_message,  id_client, id_Bus,  time_order, year, month,  day);
                O.setPeople(numP);
                String Uid=DB_model.get_DB().child("Orders").push().getKey();//maybe .child("orders")
                O.setOrder_id(Uid);

                //checking if there is a place
                DB_model.get_DB().getRef().child("Orders").child(id_Bus).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int Count=0;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String yo= ds.child("year").getValue(String.class);
                            String mo=ds.child("month").getValue(String.class);
                            String dO=ds.child("day").getValue(String.class);
                            String tO=ds.child("time").getValue(String.class);

                            if (O.getYear().equals(yo) && O.getMonth().equals(mo) && O.getDay().equals(dO) && O.getTime().equals(tO) ) {
                                Count+=ds.child("people").getValue(Integer.class);
                            }
                        }


                        if(Count+O.getPeople()>max_people_r){

                            num_people.setError("המסעדה בתפוסה מלא ");
                        }else{
                            //add the order to DB_
                            DB_Orders.addOrderToDB(O);
                            //add order to Business
                            Map<String,Object> mapB=new HashMap<>();
                            mapB.put("Orders", O);
                            DB_model.get_DB().getRef().child("Business").child(id_Bus).child("Orders").child(O.getOrder_id()).setValue(O);

                            //add order to client
                            Map<String,Object> mapC=new HashMap<>();
                            mapC.put("Orders", O);
                            DB_model.get_DB().getRef().child("Clients").child(id_client).child("Orders").child(O.getOrder_id()).setValue(O);


                            startActivity(new Intent(getApplicationContext(),Client_Activity.class));

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



        }

        public void gettingid(){
            id_client=fAuth.getUid().toString();

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