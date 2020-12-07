package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.Objects.Business;
import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class rest_diary extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    FirebaseAuth fauth;
    String id_buss;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_diary);

        fauth= FirebaseAuth.getInstance();
        id_buss=fauth.getUid().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference("Orders").child(id_buss);
        listView = (ListView) findViewById(R.id.lists);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
                String value = datasnapshot.getValue(Order.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                itemlist.clear();
//                for (DataSnapshot items : datasnapshot.getChildren()) {
//                    c = items.getValue(Client.class);
//                    itemlist.add(c.id);
//                    itemlist.add(c.mail);
//                }
//
//                adapter = new ArrayAdapter<>(rest_diary.this, android.R.layout.simple_list_item_1);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        //}


        //view by id
//        date=findViewById(R.id.datePicker1);
//        time=findViewById(R.id.spinnertime);
//        order=findViewById(R.id.order_place1);

//
//        //firebase
//        fAuth= FirebaseAuth.getInstance();
//
//        //spinner
////        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_time, android.R.layout.simple_spinner_item);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        time.setAdapter(adapter);
////        time.setOnItemSelectedListener(this);
//
//        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);
//
//
//        String Rest_name = sp.getString("restaurant name", "");
//
//        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String a = ds.child("name_rest").getValue(String.class);
//                    if (ds.child("name_rest").getValue(String.class).equals(Rest_name)) {
//                        id_Bus = ds.child("id").getValue(String.class);
//
//
//                    }
//                }
//
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String a = ds.child("name_rest").getValue(String.class);
//                    System.out.println(a);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        b.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {


        //textView.setText(id_Bus);
        //get all the value
//                String order_message=order_meal.getText().toString();
//                String time_order=time.getSelectedItem().toString();
//                String month=date.getMonth()+"";
//                String year=date.getYear()+"";
//                String day=date.getDayOfMonth()+"";
//
//                //getting restaurant name from the last activity
//                String Rest_name = sp.getString("restaurant name", "");
//
//                gettingid();
//                System.out.println("u:"+id_client+", c" +id_Bus);
//
//
//
//                //id client
//                id_client=fAuth.getUid().toString();
//
//
//                //get an new key
//                Order O= new  Order( Rest_name, order_message,  id_client, id_Bus,  time_order, year, month,  day);
//                String Uid=DB_model.get_DB().child("Orders").push().getKey();//maybe .child("orders")
//                O.setOrder_id(Uid);
//
//                //add the order to DB_
//                DB_Orders.addOrderToDB(O);
//
//                //add order to Business
//                Map<String,Object> mapB=new HashMap<>();
//                mapB.put("Orders", O);
//                DB_model.get_DB().getRef().child("Business").child(id_Bus).child("Orders").child(O.getOrder_id()).setValue(O);
//
//                //add order to client
//                Map<String,Object> mapC=new HashMap<>();
//                mapC.put("Orders", O);
//                DB_model.get_DB().getRef().child("Clients").child(id_client).child("Orders").child(O.getOrder_id()).setValue(O);
//
//
//                startActivity(new Intent(getApplicationContext(),Client_Activity.class));

//            }
//        });


        //}
    //}
}