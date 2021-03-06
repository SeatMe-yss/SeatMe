package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapplication.DB.DB_Business;
import com.example.myapplication.DB.DB_Orders;
import com.example.myapplication.DB.DB_model;
import com.example.myapplication.DB.DB_users;
import com.example.myapplication.Objects.Client;
import com.example.myapplication.Objects.Order;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class diary_client extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String id_client;
    FirebaseAuth fAuth;
    SharedPreferences sp;
    String id_order;
    String id_Bus;
    Boolean b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_client);
        Toolbar toolbar= findViewById(R.id.menu_bar2);
        setSupportActionBar(toolbar);

        fAuth= FirebaseAuth.getInstance();
        id_client=fAuth.getUid().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference("Clients").child(id_client).child("Orders");
        listView = (ListView) findViewById(R.id.lists1);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
                String value = datasnapshot.getValue(Order.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        //delete order
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                String choise =(listView.getItemAtPosition(position).toString());
                id_order = find_id(choise);

                //to find id bus
                DB_model.get_DB().getRef().child("Clients").child(id_client).child("Orders").child(id_order).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("id c:" + id_client + "\nid order:" + id_order);
                        id_Bus = snapshot.child("id_Bus").getValue(String.class);
                        System.out.println("id busss2: " + id_Bus);

//                        for (DataSnapshot ds : snapshot.getChildren()) {
//                                id_Bus = ds.child("rest_name").getValue(String.class);
//                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

                new AlertDialog.Builder(diary_client.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("שים לב")
                        .setMessage("האם את/ה בטוח/ה שברצונך/ה לבטל את התור?")
                        .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(which_item);
                                arrayAdapter.notifyDataSetChanged();

                               //System.out.println("id c:" + id_client + "\nid order:" + id_order + "\nid b:" + id_Bus);

                                DB_users.RemoveClientOrderFromDB(id_client,id_order);
                                DB_Orders.RemoveOrderFromDB(id_Bus, id_order);
                                DB_Business.RemoveBusOrderFromDB(id_Bus,id_order);

                            }
                        })

                        .setNegativeButton("לא", null)
                        .show();
            }

        });


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



    public static String find_id(String choise) {
        String temp = "";
        String id_order = "";
        int j = 0;

        for (int i = 0; i < choise.length(); i++) {
            temp += choise.charAt(i);
            if (temp.contains("מספר הזמנה: ")) {
                j = i+1;
                while (j < choise.length()) {
                    id_order += choise.charAt(j);
                    j++;

                }
                return id_order;
            }
        }
        return id_order;

    }






//    public static String find_idBus(String choise) {
//        String temp = "";
//        String id_order = "";
//        int j = 0;
//
//        for (int i = 0; i < choise.length(); i++) {
//            temp += choise.charAt(i);
//            if (temp.contains("מזהה מסעדה: ")) {
//                j = i+1;
//                while (j != choise.length()) {
//                    id_order += choise.charAt(j);
//                    j++;
//                }
//                return id_order;
//            }
//        }
//
//        return id_order;
//
//    }
}