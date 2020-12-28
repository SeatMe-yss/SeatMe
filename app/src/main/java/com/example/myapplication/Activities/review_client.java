package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.Objects.Review;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class review_client extends AppCompatActivity {

    EditText review;
    RatingBar ratingBar;
    Button save;
    SharedPreferences sp;
    FirebaseAuth fAuth;
    String id_Bus ,  id_client;
    //Spinner spinner_review;
    List<String> restaurants;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity_client);

        review=findViewById(R.id.review);
        ratingBar=findViewById(R.id.ratingBar);
        save=findViewById(R.id.button);
        //spinner_review = findViewById(R.id.spinner4);
        Toolbar toolbar= findViewById(R.id.menu_bar8);
        setSupportActionBar(toolbar);

        //spinner of resurant from DB
        //restaurants = new ArrayList<String>();

        fAuth= FirebaseAuth.getInstance();
        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);
        String Rest_name1 = sp.getString("restaurant name", "");

        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("name_rest").getValue(String.class).equals(Rest_name1)) {
                        id_Bus = ds.child("id").getValue(String.class);
                        System.out.println("id busss: " + id_Bus);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String review_message=review.getText().toString();
                float numStars = ratingBar.getRating();

                String Rest_name = sp.getString("restaurant name", "");
                gettingid();
                id_client=fAuth.getUid().toString();

                //get an new key
                Review r = new Review( review_message, numStars);
                r.setNumStars(numStars);
                r.setReview_message(review_message);
                String Uid=DB_model.get_DB().child("Business").child(id_Bus).child("Reviews").push().getKey();//maybe .child("orders")
                r.setReview_id(Uid);

                //put revies in db business
                DB_model.get_DB().getRef().child("Business").child(id_Bus).child("Reviews").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //add order to Business
                        Map<String, Object> mapB = new HashMap<>();
                        mapB.put("Reviews", review_message);
                        DB_model.get_DB().getRef().child("Business").child(id_Bus).child("Reviews").child(r.getReview_id()).setValue(r);
                        startActivity(new Intent(review_client.this, Client_Activity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }



        });

//        //initlized the resturants spinner by DB
//        DB_model.get_DB().getRef().child("Business").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    restaurants.add(ds.child("name_rest").getValue(String.class));
//                }
//                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(review_client.this, android.R.layout.simple_spinner_dropdown_item, restaurants);
//                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//                spinner_review.setAdapter(spinnerArrayAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
       // spinner_review.setOnItemSelectedListener(this);


        //shared preference
       // sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);


    }



    // create the 3 dots at login_register activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // click on some item un the menu bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, login_register.class);
                startActivity(intent);
                finish();
                break;

            case R.id.action_myOrders:
                Intent intent1 = new Intent(this, diary_client.class);
                startActivity(intent1);
                break;


            case R.id.action_backToMain:
                Intent intent2 = new Intent(this, Client_Activity.class);
                startActivity(intent2);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }



    public void gettingid(){
        id_client=fAuth.getUid().toString();
    }
}