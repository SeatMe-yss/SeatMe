package com.example.myapplication.DB;
import com.google.firebase.database.*;

public class DB_model {

        protected DatabaseReference ref;

        public DB_model(){ ref = FirebaseDatabase.getInstance().getReference(); }
    }

