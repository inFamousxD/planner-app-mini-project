package com.example.appdraweractivitytest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FeedbackPage extends AppCompatDialogFragment {
    public static String feedbackStringStatic, feedbackIdStatic;
    public EditText feedbackString;
    public Button sendFeedback, cancelFeedback;
    private DatabaseReference databaseReference;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();

        databaseReference = FirebaseDatabase.getInstance().getReference("feedback");

        View view = inflater.inflate(R.layout.feedback_dialog, null);
        builder.setView(view);

        feedbackString = view.findViewById(R.id.feedbackText);
        sendFeedback = view.findViewById(R.id.sendFeedback);
        cancelFeedback = view.findViewById(R.id.cancelFeedback);

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbackStringStatic = feedbackString.getText().toString();
                if (feedbackStringStatic.isEmpty())
                    feedbackString.setError("Task name cannot be empty.");
                else {
                    feedbackIdStatic = databaseReference.push().getKey();
                    FeedbackNode node = new FeedbackNode(LoginPage.passwd, feedbackStringStatic);

                    databaseReference.child(feedbackIdStatic).child("uid").setValue(LoginPage.passwd);
                    databaseReference.child(feedbackIdStatic).child("feedback").setValue(feedbackStringStatic);
                    Toast.makeText(getActivity(), "task added", Toast.LENGTH_SHORT).show();
                    dismiss();
                }

            }
        });
        cancelFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }
}
