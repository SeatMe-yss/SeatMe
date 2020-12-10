package com.example.myapplication.DB;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DB_Orders {



    public static void addOrderToDB(Order O){
        DB_model.get_DB().getRef().child("Orders").child(O.getId_Bus()).child(O.getOrder_id()).setValue(O);
        O.setOrder_id(O.getOrder_id());

    }


    public static void RemoveOrderFromDB(String id_rest, String id_order){
        DB_model.get_DB().getRef().child("Orders").child(id_rest).child(id_order).setValue(null);

    }

}
