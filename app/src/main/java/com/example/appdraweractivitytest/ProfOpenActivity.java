package com.example.appdraweractivitytest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfOpenActivity extends AppCompatActivity {

    private TextView textViewName, textViewEmail, textViewContact, textViewPassword, textViewScore;
    public static Integer pending_task_count, login_frequency_count, completed_tasks_count, added_tasks_count;
    private Integer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_open);

        textViewName = findViewById(R.id.profName);
        textViewEmail = findViewById(R.id.emailAddress);
        textViewContact = findViewById(R.id.messageNo);
        textViewPassword = findViewById(R.id.employee_id_number);
        textViewScore = findViewById(R.id.streak_value);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users").child(LoginPage.passwd);

        mDatabase.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String profileName = dataSnapshot.getValue(String.class);
                textViewName.setText(profileName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String emailAddress = dataSnapshot.getValue(String.class);
                textViewEmail.setText(emailAddress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String contactNumber = dataSnapshot.getValue(String.class);
                textViewContact.setText(contactNumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String contactNumber = dataSnapshot.getValue(String.class);
                textViewScore.setText(contactNumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("password").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String contactNumber = dataSnapshot.getValue(String.class);
                textViewPassword.setText(contactNumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //===================================================================================
        mDatabase.child("stats").child("complete_tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                completed_tasks_count = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("stats").child("pending_tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pending_task_count = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("stats").child("login_frequency").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                login_frequency_count = dataSnapshot.getValue(Integer.class);
                buffer = login_frequency_count;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("stats").child("tasks_added").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                added_tasks_count = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}