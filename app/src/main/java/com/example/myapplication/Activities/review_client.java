package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.Objects.Review;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class review_client extends AppCompatActivity {

    EditText review;
    RatingBar ratingBar;
    Button save;
    SharedPreferences sp1;
    FirebaseAuth fAuth;
    String id_Bus ,  id_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity_client);

        review=findViewById(R.id.review);
        ratingBar=findViewById(R.id.ratingBar);
        save=findViewById(R.id.button);
        Toolbar toolbar= findViewById(R.id.menu_bar4);
        setSupportActionBar(toolbar);

        fAuth= FirebaseAuth.getInstance();
        sp1=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);
        String Rest_name1 = sp1.getString("restaurant name", "");

        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    //String a = ds.child("name_rest").getValue(String.class);
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
                int numStars = ratingBar.getNumStars();

                String Rest_name = sp1.getString("restaurant name", "");
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
    }

    public void gettingid(){
        id_client=fAuth.getUid().toString();
    }
}