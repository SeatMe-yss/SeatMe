package com.example.myapplication.DB;


import com.example.myapplication.Objects.Order;

public class DB_Orders {


    public static void addOrderToDB(Order O){
        System.out.println("the id:"+O.getOrder_id());
        DB_model.get_DB().getRef().child("Orders").child(O.getOrder_id()).setValue(O);
    }

}
