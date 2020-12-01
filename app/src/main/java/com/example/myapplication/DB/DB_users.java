package com.example.myapplication.DB;

import androidx.annotation.NonNull;

import com.example.myapplication.Objects.Client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class DB_users extends DB_model {

     private DatabaseReference dbRef = ref.getReference("Clients");

    public static void addUserToDB(String mail, String password, String phone, String id) {
        Client C = new Client(mail, password, phone, id);
        DB_model.get_DB().getReference().child("Clients").child(id).setValue(C);

    }


    public boolean isClient(String id1) {
       dbRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        String id= snapshot.child("id").getValue().toString();
        if(id==id1) return true;
    }


}











}
