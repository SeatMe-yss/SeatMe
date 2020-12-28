package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.DB.DB_Business;
import com.example.myapplication.DB.DB_model;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import static android.R.layout.simple_spinner_item;


public class Client_Activity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    Button made_reservation;
    Button menu;
    Button rank;
    Button myOrder;
    Button logout;
    Spinner spinner_rest;
    SharedPreferences sp;
    List<String> restaurants;
    public static final String SHARED_PRE = "sharedPrefs";
    String r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_activity);
        //find view by id
        made_reservation = findViewById(R.id.resevartion);
        menu = findViewById(R.id.menu);
        rank = findViewById(R.id.order_place1);
        myOrder = findViewById(R.id.order);
        logout = findViewById(R.id.logout);
        spinner_rest = findViewById(R.id.branch_sppiner);
        Toolbar toolbar= findViewById(R.id.menu_bar1);
        setSupportActionBar(toolbar);


        //spinner of resurant from DB
        restaurants = new ArrayList<String>();

        //initlized the resturants spinner by DB
        DB_model.get_DB().getRef().child("Business").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    restaurants.add(ds.child("name_rest").getValue(String.class));
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Client_Activity.this, android.R.layout.simple_spinner_dropdown_item, restaurants);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinner_rest.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        spinner_rest.setOnItemSelectedListener(this);
        //set on clicklistener
        made_reservation.setOnClickListener(this);
        menu.setOnClickListener(this);
        rank.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        logout.setOnClickListener(this);

        //shared preference
        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);


    }


    // create the 3 dots at login_register activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // click on some item un the menu bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, login_register.class);
                startActivity(intent);
                finish();
                break;

            case R.id.action_myOrders:
                Intent intent1 = new Intent(this, diary_client.class);
                startActivity(intent1);
                break;


            case R.id.action_backToMain:
                Intent intent2 = new Intent(this, Client_Activity.class);
                startActivity(intent2);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v==made_reservation){
            int t=spinner_rest.getSelectedItemPosition();
            r= (String)spinner_rest.getAdapter().getItem(t);

            //pass the restaurant name to the next activity
            if(r.equals("בחר מסעדה")){
                ((TextView)spinner_rest.getSelectedView()).setError("בחר מסעדה");

            }
            else{//passing the resturant name
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("restaurant name", r);
                editor.apply();

                //starting the new activity
                Intent intent= new Intent(this, order_place.class );
                startActivity(intent);
            }

        }
        else if(v==logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(this, login_register.class );
            startActivity(intent);
            finish();

        }
        else if(v==myOrder){
            Intent intent= new Intent(this, diary_client.class );
            startActivity(intent);

        }

        else if(v==menu){//just checking
            Intent intent= new Intent(this, menue_client.class );
            startActivity(intent);
        }

        else if(v==rank){
            int t=spinner_rest.getSelectedItemPosition();
            r= (String)spinner_rest.getAdapter().getItem(t);

            //pass the restaurant name to the next activity
            if(r.equals("בחר מסעדה")){
                ((TextView)spinner_rest.getSelectedView()).setError("בחר מסעדה");

            }
            else{//passing the resturant name
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("restaurant name", r);
                editor.apply();

                //starting the new activity
                Intent intent= new Intent(this, review_client.class );
                startActivity(intent);
            }

        }



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}