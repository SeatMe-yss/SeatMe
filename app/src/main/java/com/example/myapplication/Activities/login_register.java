package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class login_register extends AppCompatActivity implements View.OnClickListener{
    Button login;
    Button register;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
       //view by id
        login=findViewById(R.id.sign_in);
        register=findViewById(R.id.register1);
        //setOnClickListener
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        Toolbar toolbar= findViewById(R.id.menu_bar);
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
    public void onClick(View v) {
        if(v==login){
            Intent intent= new Intent(this,login.class );
            startActivity(intent);
        }
        else if(v==register){
            Intent intent= new Intent(this,register.class) ;
            startActivity(intent);
        }
    }
}
