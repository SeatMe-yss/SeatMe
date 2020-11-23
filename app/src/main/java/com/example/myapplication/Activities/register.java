package com.example.myapplication.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.BreakIterator;

public class register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button register;
    Spinner spinner_type;
    String[] types;
    TextView mail;
    TextView password;
    TextView phone;
    private FirebaseAuth mAuth;
    Dialog d;
    FirebaseAuth firebaseAuth;
    AlertDialog progressDialog;
    BreakIterator btnMainLogin;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register=(Button)findViewById(R.id.register2);
        register.setOnClickListener(this);
        spinner_type=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        spinner_type.setOnItemSelectedListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v==register){
            Intent intent= new Intent(this, Client_Activity.class );
            startActivity(intent);
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


    public void createRegisterDialog()
    {

        d= new Dialog(this);
        d.setContentView(R.layout.register);
        d.setTitle("Register");
        d.setCancelable(true);
        mail=(EditText)d.findViewById(R.id.username);
        password=(EditText)d.findViewById(R.id.password);
        phone=(EditText)d.findViewById(R.id.phone);
        d.show();
    }

    public void register()
    {
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText()
                .toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(register.this, "Successfully registered",
                            Toast.LENGTH_LONG).show();

                    btnMainLogin.setText("Logout");
                } else {
                    Toast.makeText(register.this, "Registration Error",
                            Toast.LENGTH_LONG).show();
                }
                d.dismiss();
                progressDialog.dismiss();
            }
        });
    }




}
