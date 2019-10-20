package com.example.appdraweractivitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    FirebaseAuth mFireBaseAuth;
    public EditText emailId, password;
    public static String passwd, email;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);

        mFireBaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailId.getText().toString();
                passwd = password.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter a valid Email address.");
                    emailId.requestFocus();
                }
                else if (passwd.isEmpty()) {
                    password.setError("Password field should not be Empty.");
                    password.requestFocus();
                }
                else if (!(email.isEmpty() && passwd.isEmpty())) {
                    mFireBaseAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(LoginPage.this, "Couldn't log you in at this moment.", Toast.LENGTH_SHORT);
                            }
                            else {
                                Toast.makeText(LoginPage.this, "Logging in..", Toast.LENGTH_SHORT);

                                startActivity(new Intent(LoginPage.this, MainActivity.class));
                            }

                        }
                    });
                }
            }
        });

    }
}
