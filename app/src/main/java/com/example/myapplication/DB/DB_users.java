package com.example.myapplication.DB;

import com.example.myapplication.Objects.Client;

public class DB_users {

    public static void addUserToDB(String mail, String password,String phone,String id){
        Client C=new Client(mail, password,phone,id);
        DB_model.get_DB().child("Clients").child(id).setValue(C);

    }














}
