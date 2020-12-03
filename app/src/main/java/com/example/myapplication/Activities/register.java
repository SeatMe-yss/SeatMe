package com.example.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.DB_Business;
import com.example.myapplication.DB.DB_users;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button register;
    Spinner spinner_type;
    String[] types;
    EditText mail,password, phone;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //view by id
        register=findViewById(R.id.register2);
        mail=findViewById(R.id.useremail);
        password=findViewById(R.id.password);
        phone=findViewById(R.id.phone);
        spinner_type=findViewById(R.id.spinner2);
        //spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        spinner_type.setOnItemSelectedListener(this);
        //firebase
        fAuth = FirebaseAuth.getInstance();

        //if the user already register
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Client_Activity.class));
            finish();
        }





        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user_mail=mail.getText().toString();
                String user_password=password.getText().toString();
                String user_phone=phone.getText().toString();
                String type=spinner_type.getSelectedItem().toString();
                //checking error
                if(TextUtils.isEmpty(user_mail)){
                    mail.setError("Email isn't vaild");
                    return;
                }
                if(TextUtils.isEmpty(user_password)){
                    password.setError("Password isn't vaild");
                    return;
                }
                if(TextUtils.isEmpty(user_password)){
                    phone.setError("Phone isn't vaild");
                    return;
                }
                if(user_password.length()<6){
                    password.setError("Password must be more than 6 chars");
                    return;
                }
                //register

                fAuth.createUserWithEmailAndPassword(user_mail,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //enter to db
                            String user_id=fAuth.getUid();
                          if(type.equals("לקוח") ){
                             DB_users.addUserToDB(mail.getText().toString(), password.getText().toString(),phone.getText().toString(),user_id);
                             startActivity(new Intent(getApplicationContext(),Client_Activity.class));
                          }
                          else if(type.equals("בית עסק") ) {
                              DB_Business.addBusinessToDB(mail.getText().toString(), password.getText().toString(),phone.getText().toString(),user_id);
                              startActivity(new Intent(getApplicationContext(),activity_rest.class));
                          }
                        }else{
                            mail.setError("InVaild");
                        }
                    }
                });

            }
        });

    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {




    }


}
