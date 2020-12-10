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
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.DB.DB_Business;
import com.example.myapplication.DB.DB_model;
import com.example.myapplication.DB.DB_users;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button sign_in;
    EditText mail, password;
    Spinner spinner_type;
    String[] types;
    FirebaseAuth fAuth;
    boolean isC1=false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //find view by id
        sign_in = findViewById(R.id.login1);
        mail = findViewById(R.id.useremail);
        password = findViewById(R.id.password3);

        //setOnClickListener
        sign_in.setOnClickListener(this);
        mail.setOnClickListener(this);
        password.setOnClickListener(this);

        //spinner
        spinner_type = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        spinner_type.setOnItemSelectedListener(this);

        //    FirebaseAuth
        fAuth = FirebaseAuth.getInstance();
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String user_mail = mail.getText().toString();
                String user_password = password.getText().toString();

                //checking error
                if (TextUtils.isEmpty(user_mail)) {
                    mail.setError("Email isn't vaild");
                    return;
                }
                if (TextUtils.isEmpty(user_password)) {
                    password.setError("Password isn't vaild");
                    return;
                }
                if (user_password.length() < 6) {
                    password.setError("Password must be more than 6 chars");
                    return;
                }

                if (v == sign_in) {
                    fAuth.signInWithEmailAndPassword(user_mail, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String user_id=fAuth.getUid();
                               // DB_users.isClient(user_id);

                                DB_model.get_DB().child("Clients").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for(DataSnapshot s: snapshot.getChildren()){
                                            if(s.child("id").getValue().equals(user_id)){
                                                isC1=true;

                                            }

                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                               String type = spinner_type.getSelectedItem().toString();
                                System.out.println(type);
                                if (type.equals("לקוח") && isC1) {
                                    System.out.println(isC1);
                                    startActivity(new Intent(getApplicationContext(), Client_Activity.class));
                                }
                                else if (type.equals("בית עסק") && !isC1 ) {
                                    System.out.println(isC1);
                                    startActivity(new Intent(getApplicationContext(), activity_rest.class));

                                }
                                else{
                                    ((TextView)spinner_type.getSelectedView()).setError("המשתמש שנבחר אינו מתאים");
                                }

                            } else {
                                 mail.setError("המייל או הסיסמה אינם תקינים");
                                 password.setError("המייל או הסיסמה אינם תקינים");

                            }
                        }
                    });

                }
            }


        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
