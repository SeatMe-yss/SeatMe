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

import com.example.myapplication.DB.DB_model;
import com.example.myapplication.DB.DB_users;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import java.text.BreakIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.validation.Validator;

public class register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button register;
    Spinner spinner_type;
    String[] types;
    EditText mail;
    EditText password;
    EditText phone;
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
        firebaseAuth = FirebaseAuth.getInstance();

    }


        @Override
    public void onClick(View v) {
        if(v==register){
            Intent intent= new Intent(this, Client_Activity.class );
            startActivity(intent);
            String user_mail=mail.getText().toString();
            String user_password=password.getText().toString();
            String user_phone=phone.getText().toString();



                //            if(Valid_User(user_mail,user_password,user_phone)){
                register(user_mail,user_password,user_phone);
//            }
        }
    }

    private boolean Valid_User(String user_mail, String user_password, String user_phone) {
        if(user_mail.isEmpty() || user_password.isEmpty() || user_phone.isEmpty()){
            Toast.makeText(register.this, "Complete all the field please ", Toast.LENGTH_SHORT).show();
            return false ;
        }
        if(!emailValidator(user_mail)){
            Toast.makeText(register.this, "The email isn't valid ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!validCellPhone(user_phone)){
            Toast.makeText(register.this, "The phone isn't valid ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;


    }

    /*function that check the email if is valid (from https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext)*/
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /* code to check the phone number from:
    https://stackoverflow.com/questions/6358380/phone-number-validation-android*/
    public boolean validCellPhone(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


//    public void createRegisterDialog()
//    {
//
//        d= new Dialog(this);
//        d.setContentView(R.layout.register);
//        d.setTitle("Register");
//        d.setCancelable(true);
//        mail=(EditText)d.findViewById(R.id.username);
//        password=(EditText)d.findViewById(R.id.password);
//        phone=(EditText)d.findViewById(R.id.phone);
//        d.show();
//    }

    public void register(String user_mail,String user_password,String user_phone)
    {

        Toast.makeText(register.this, "Regitering please wait....",Toast.LENGTH_LONG).show();
        firebaseAuth.createUserWithEmailAndPassword(user_mail,user_password).addOnCompleteListener
                (this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(register.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    FirebaseUser user=firebaseAuth.getCurrentUser();
//                    updateUI(user,user_phone,user_mail,user_password);
//                    btnMainLogin.setText("Logout");
                } else {
                    Toast.makeText(register.this, "Registration Error",Toast.LENGTH_LONG).show();
                }
//                d.dismiss();
//                progressDialog.dismiss();
            }

            private void updateUI(FirebaseUser user, String user_phone, String user_mail, String user_password) {
               String user_id=user.getUid();
                DB_users.addUserToDB(user_mail,user_password,user_phone,user_id);


            }


        });
    }




}
