package com.example.appdraweractivitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class Task_MainScreen extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference mDatabase;
    private List<String> listArray;
    public static String taskExpandName, taskExpandDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testforlistview);

        mDatabase = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd);
        listArray = new ArrayList<>();
        listView = findViewById(R.id.testListView);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.testforlistview, R.id.testTextViewBuffer, listArray);
        listView.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String string = dataSnapshot.getValue(String.class);
                listArray.add(string);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), listArray.get(i), Toast.LENGTH_SHORT).show();
                taskExpandName = listArray.get(i);
                expandTask(taskExpandName);
            }
        });
    }
    public void expandTask(String taskExpandName) {
        TaskExpand expand = new TaskExpand(taskExpandName,"Default");
        expand.show(getSupportFragmentManager(), "expand task");
    }
}
