package com.example.myapplication.DB;

import androidx.annotation.NonNull;

import com.example.myapplication.Objects.Business;
import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DB_Business {

    public static void addBusinessToDB(String mail, String password,String phone,String id){
        Business B=new Business(mail, password,phone,id);
        DB_model.get_DB().getRef().child("Business").child(id).setValue(B);
    }
    //add the rest name
    public static void addRestNameToDB(String r, Business B){

    }

    public static void addOrdertoBusiness(Business B ,Order O){
        B.addOrder(O);
        DB_model.get_DB().getRef().child("Business").child(B.getId()).setValue(O);
    }
//
//    public static List<String> getresturants(){
//        List <String> r=new ArrayList<>();
//
//        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            String name=snapshot.child("name_rest"
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }



    }
