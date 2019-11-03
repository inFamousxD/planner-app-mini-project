package com.example.appdraweractivitytest;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatsActivity extends AppCompatActivity {

    public static boolean done = false;
    private TextView login_count;
    private TextView tasks_added;
    private TextView pending_tasks;
    private TextView completed_tasks;
    private Integer bufferInt;
    private int countPending, countComplete;
    private String bufferString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_activity);

        login_count = findViewById(R.id.times_logged_in);
        tasks_added = findViewById(R.id.added_tasks);
        pending_tasks = findViewById(R.id.pending_tasks);
        completed_tasks = findViewById(R.id.completed_tasks);

        login_count.setText(Integer.toString(MainActivity.totalCount));

        bufferInt = ProfOpenActivity.added_tasks_count;
        bufferString = bufferInt.toString();
        tasks_added.setText(bufferString);

        final DatabaseReference countPendingRef = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status");
        countPendingRef.child("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countPending = (int) dataSnapshot.getChildrenCount();
                pending_tasks.setText(Integer.toString(countPending));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference countCompleteRef = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status");
        countCompleteRef.child("complete").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countComplete = (int) dataSnapshot.getChildrenCount();
                completed_tasks.setText(Integer.toString(countComplete));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
