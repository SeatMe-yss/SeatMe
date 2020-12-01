package com.example.myapplication.DB;

import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;

public class DB_Orders {

    public static void addOrderToDB(String rest_name, String Order_id,String Order_meal, String time,String year,String month, String day){
        Order O = new Order(rest_name,Order_id,Order_meal,time,year,month,day);
        DB_model.get_DB().getRef().child("Order").child(Order_id).setValue(O);
    }
}
