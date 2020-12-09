package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.DB.DB_model;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;

public class change_my_rest extends AppCompatActivity {

    EditText rest_name;
    EditText num_people;
    Button save;
    FirebaseAuth fAuth;
    int max_people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_rest);
        //view by id
        rest_name=findViewById(R.id.rest_name2);
        num_people=findViewById(R.id.num_people);
        save=findViewById(R.id.save);

        //firebase
        fAuth = FirebaseAuth.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r=rest_name.getText().toString();
                String u= fAuth.getUid();
                //update the rest name
               Map<String,Object> map=new HashMap<>();
               map.put("name_rest", r);
               DB_model.get_DB().getRef().child("Business").child(u).updateChildren(map);

               //if the num we get is vaild
                String n=num_people.getText().toString();
                try{
                    max_people=Integer.parseInt(n);
                }catch (Exception e){
                    num_people.setError("הכנס מספר תקין");
                }
                //update num people
                Map<String,Integer> map1=new HashMap<>();
                map.put("max_people", max_people);
                DB_model.get_DB().getRef().child("Business").child(u).updateChildren(map);
                //pass to the screen before
                startActivity(new Intent(getApplicationContext(), activity_rest.class));


            }
        });


    }




}