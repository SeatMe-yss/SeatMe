package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class activity_rest extends AppCompatActivity implements View.OnClickListener {
//    Button menu;
    Button review;
    Button diary;
    Button change;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        //find view by id
//        menu=findViewById(R.id.my_menu);

        review=findViewById(R.id.my_review);
        diary=findViewById(R.id.my_diary);
        change=findViewById(R.id.my_change);
        logout=findViewById(R.id.my_logout);
        Toolbar toolbar= findViewById(R.id.menu_bar5);
        setSupportActionBar(toolbar);


        //set on clicklistener
//        menu.setOnClickListener(this);
        review.setOnClickListener(this);
        diary.setOnClickListener(this);
        change.setOnClickListener(this);
        logout.setOnClickListener(this);

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
                Intent intent1 = new Intent(this, rest_diary.class);
                startActivity(intent1);
                break;


            case R.id.action_backToMain:
                Intent intent2 = new Intent(this, activity_rest.class);
                startActivity(intent2);
                break;

            default:
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if(v==logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent= new Intent(this, login_register.class );
            startActivity(intent);
            finish();
        }
//        else if(v==menu){
//
//        }
        else if(v==review){
            Intent intent = new Intent(this, review_bus.class);
            startActivity(intent);
        }
        else if(v==diary){
            Intent intent = new Intent(this, rest_diary.class);
            startActivity(intent);


        }
        else if(v==change){

            Intent intent= new Intent(this, change_my_rest.class );
            startActivity(intent);

        }

    }
}