package com.example.myapplication.DB;


import com.example.myapplication.Objects.Order;

public class DB_Orders {


    public static void addOrderToDB(Order O){

        String rest_name = O.getRest_name();
        DB_model.get_DB().getRef().child("Orders").child(O.getId_Bus()).child(O.getOrder_id()).setValue(O);

    }

}
