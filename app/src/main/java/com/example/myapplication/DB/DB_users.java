package com.example.myapplication.DB;

import androidx.annotation.NonNull;

import com.example.myapplication.Objects.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class DB_users extends DB_model {

    public static boolean isC ;  //declare and assign default value in global scope
//    static DatabaseReference dbRef = ref.getRef("Clients");

    public static void addUserToDB(String mail, String password, String phone, String id) {
        Client C = new Client(mail, password, phone, id);
        DB_model.get_DB().getRef().child("Clients").child(id).setValue(C);

    }


    public static void RemoveClientOrderFromDB(String id_client, String id_order){
        DB_model.get_DB().getRef().child("Clients").child(id_client).child("Orders").child(id_order).setValue(null);

    }




    public static DatabaseReference check_isClient(){
        String user_id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        return  DB_model.get_DB().getRef().child("Clients").child(user_id);
    }




    public static void isClient(String id1) {
        DatabaseReference dr= DB_model.get_DB().getRef().child("Clients").child(id1);
        //String id_us = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    DB_users.isC=true;
                }
                else{
                    DB_users.isC=false;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}





