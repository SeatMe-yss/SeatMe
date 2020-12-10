package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
    String id_Bus;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_client);

        fAuth= FirebaseAuth.getInstance();
        id_client=fAuth.getUid().toString();

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                //id_client = fAuth.getUid().toString();

                String choise =(listView.getItemAtPosition(position).toString());
                String id_order = find_id(choise);
                System.out.println("Syso id:" + id_order);

                new AlertDialog.Builder(diary_client.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(which_item);
                                arrayAdapter.notifyDataSetChanged();

                                System.out.println("id c:" + id_client + "\nid order:" + id_order + "\nid b:" + id_Bus + "\nid o:" + id_order);
                                DB_users.RemoveClientOrderFromDB(id_client,id_order);
                                DB_Orders.RemoveOrderFromDB(id_Bus, id_order);
                                DB_Business.RemoveBusOrderFromDB(id_Bus,id_order);

                            }
                        })

                        .setNegativeButton("No", null)
                        .show();


            }


        });

//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        Query applesQuery = ref.child("Orders").child(id_Bus).child("-MO8TpSVkSIFE_O1gS4M1gS4M").equalTo("-MO8TpSVkSIFE_O1gS4M1gS4M");
//
//        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//                    appleSnapshot.getRef().removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                //Log.e(TAG, "onCancelled", databaseError.toException());
//            }
//        });

    }

    public static String find_id(String choise) {
        char c = ' ';
        String id_order = "";
        int j = 0;

        for (int i = 0; i < choise.length(); i++) {
            c = choise.charAt(i);
            //System.out.println("c = " +c);
            if (c == 'I') {
                j = i+4;
                while (j != choise.length()) {
                    id_order += choise.charAt(j);
                    //System.out.println("id order = " +id_order);
                    j++;
                }
                return id_order;
            }
        }
        System.out.println("id order sysoo:" + id_order);
        return id_order;

    }
}