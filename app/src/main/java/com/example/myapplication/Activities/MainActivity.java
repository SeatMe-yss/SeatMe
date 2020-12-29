package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static int WELCOME_TIMEOUT = 2000;

    //Button logo;
    ImageButton logo;
//    Button register;
//    TextView welcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(MainActivity.this, login_register.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, WELCOME_TIMEOUT);





        logo=findViewById(R.id.imageButton2);
        //register=(Button)findViewById(R.id.register1);
        logo.setOnClickListener(this);
        //register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this,login_register.class );
        startActivity(intent);

    }
}