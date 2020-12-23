package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class menue_client extends AppCompatActivity {


    ImageView menu_photo;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();
    private DatabaseReference first=databaseReference.child("Business");
    SharedPreferences sp;
    String id_Bus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_client);

        menu_photo=findViewById(R.id.menu_rest);



    }

    @Override
    protected void onStart() {
        super.onStart();
        //getting id of resturant
        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);
        String Rest_name = sp.getString("restaurant name", "");

        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String a = ds.child("name_rest").getValue(String.class);
                    if (ds.child("name_rest").getValue(String.class).equals(Rest_name)) {
                        id_Bus = ds.child("id").getValue(String.class);


                    }
                }
                String link= snapshot.child(id_Bus).child("Menu_url").getValue(String.class);
                System.out.println("link: "+link);
                Picasso.get().load(link).into(menu_photo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}