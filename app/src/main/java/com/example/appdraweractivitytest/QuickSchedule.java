package com.example.appdraweractivitytest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuickSchedule extends AppCompatDialogFragment {

    public EditText taskName, taskDescription;
    public Button addTask, cancelAddTask;
    public static String taskNameString, taskIdString;
    DatabaseReference databaseReference;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(LoginPage.passwd).child("status").child("pending");

        View view = inflater.inflate(R.layout.new_task_popup, null);
        builder.setView(view);

        taskName = view.findViewById(R.id.newTaskText);
        addTask = view.findViewById(R.id.addTask);
        cancelAddTask = view.findViewById(R.id.cancelAddTask);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskNameString = taskName.getText().toString();
                if (taskNameString.isEmpty())
                    taskName.setError("Task name cannot be empty.");
                else {
                    taskIdString = databaseReference.push().getKey();
                    Tasks task = new Tasks(taskNameString, taskIdString);

                    databaseReference.child(taskIdString).setValue(taskNameString);
                    Toast.makeText(getActivity(), "task added", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

            }
        });
        cancelAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }
}
