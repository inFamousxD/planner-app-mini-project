package com.example.appdraweractivitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestStorageActivity extends AppCompatActivity {

    Button uploadTask;
    EditText taskName, taskDescription;
    DatabaseReference mDatabase;
    public String task, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);

        uploadTask = findViewById(R.id.uploadTask);
        taskName = findViewById(R.id.taskName);
        taskDescription = findViewById(R.id.taskDescription);

        task = taskName.getText().toString();
        desc = taskDescription.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        uploadTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tasks taskUpload = new Tasks(task, desc);
                mDatabase.child("tasks").child("test").setValue(taskUpload);
            }
        });

    }
}
