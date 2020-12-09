package com.example.myapplication.DB;


import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DB_Orders {


    public static void addOrderToDB(Order O){
        DB_model.get_DB().getRef().child("Orders").child(O.getId_Bus()).child(O.getOrder_id()).setValue(O);
        O.setOrder_id(O.getOrder_id());

    }


    public static void RemoveOrderFromDB(String id){
        DB_model.get_DB().getRef().child("Orders").child(id).setValue(null);
//        DatabaseReference d = FirebaseDatabase.getInstance().getReference("Orders").child(id);
//        d.removeValue();



    }

}
