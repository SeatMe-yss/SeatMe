package com.example.myapplication.DB;

import com.example.myapplication.Objects.Business;
import com.example.myapplication.Objects.Client;

public class DB_Business {

    public static void addBusinessToDB(String mail, String password,String phone,String id){
        Business B=new Business(mail, password,phone,id);
        DB_model.get_DB().getRef().child("Business").child(id).setValue(B);
    }


}
