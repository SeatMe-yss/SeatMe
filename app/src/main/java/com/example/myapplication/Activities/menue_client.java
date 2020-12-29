package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class menue_client extends AppCompatActivity {


    ImageView menu_photo;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();
    private DatabaseReference first=databaseReference.child("Business");
    SharedPreferences sp;
    String id_Bus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_client);
        menu_photo=findViewById(R.id.menu_rest);
        Toolbar toolbar= findViewById(R.id.menu_bar3);
        setSupportActionBar(toolbar);

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
    protected void onStart() {
        super.onStart();
        //getting id of resturant
        sp=getSharedPreferences("restaurant name", Context.MODE_PRIVATE);
        String Rest_name = sp.getString("restaurant name", "");

        DB_model.get_DB().getRef().child("Business").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String a = ds.child("name_rest").getValue(String.class);
                    if (ds.child("name_rest").getValue(String.class).equals(Rest_name)) {
                        id_Bus = ds.child("id").getValue(String.class);


                    }
                }
                String link= snapshot.child(id_Bus).child("Menu_url").getValue(String.class);
                Picasso.get().load(link).into(menu_photo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}