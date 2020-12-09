package com.example.myapplication.DB;
import com.example.myapplication.Objects.Client;
import com.google.firebase.database.*;

public class DB_model {

    protected static DatabaseReference ref;


    public static DatabaseReference get_DB(){
        ref = FirebaseDatabase.getInstance().getReference();
        return ref;
    }






}