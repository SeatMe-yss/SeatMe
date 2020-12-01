package com.example.myapplication.DB;
import com.example.myapplication.Objects.Client;
import com.google.firebase.database.*;

public class DB_model {

    protected static FirebaseDatabase ref;


    public static FirebaseDatabase get_DB(){
        ref = FirebaseDatabase.getInstance();
        return ref;
    }

//    public static boolean search_client(String id){
//
//        return false;
//
//
//    }



}