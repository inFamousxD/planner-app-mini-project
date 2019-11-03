package com.example.appdraweractivitytest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    private TextView feedbackTextView;
    //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("feedback");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        feedbackTextView = findViewById(R.id.feedback);
        //logoutTextView = findViewById(R.id.logout);

        feedbackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackPage feedback = new FeedbackPage();
                feedback.show(getSupportFragmentManager(), "feedback dialog");

            }
        });
    }
}
