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
    static boolean CheckExists = false;   //declare and assign default value in global scope
    static DatabaseReference dbRef = ref.getReference("Clients");

    public static void addUserToDB(String mail, String password, String phone, String id) {
        Client C = new Client(mail, password, phone, id);
        DB_model.get_DB().getReference().child("Clients").child(id).setValue(C);

    }


    public static boolean isClient(String id1) {

        dbRef.child("Clients").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> userChildren = dataSnapshot.getChildren();

                for (DataSnapshot user : userChildren) {
                    Client u = user.getValue(Client.class);

                    if (u.getId()==(id1)) {
                        CheckExists = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        return CheckExists;
    }
}





//       dbRef.addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError error) {
//
//           }
//       });
















