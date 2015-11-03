package com.example.rohan.arms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class Login extends AppCompatActivity {

    Button bLogin;
    ProgressDialog progressDialog;
    EditText etEmail;
    EditText etPassword;
    View parentLayout;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://popping-inferno-2396.firebaseio.com/");

        ref.createUser("arms@forkthecode.com", "miniproject", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                System.out.println("Successfully created user account with uid: " + stringObjectMap.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {

            }
        });


        bLogin = (Button)findViewById(R.id.login2);
        etEmail = (EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);
        parentLayout = findViewById(R.id.view);
        bRegister = (Button)findViewById(R.id.registernewuser);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in...");
        progressDialog.setMessage("This may take a while...");

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    progressDialog.dismiss();
                    etEmail.setError("Enter Email");
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    progressDialog.dismiss();
                    etPassword.setError("Enter Password");
                    return;
                }

                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        progressDialog.dismiss();
                        Intent i = new Intent(Login.this, Menu.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        progressDialog.dismiss();
                        if (etEmail.getText().toString().equals("arms@forkthecode.com") && !(etPassword.getText().toString().equals("miniproject")))
                            Snackbar.make(parentLayout, "Incorrect password", Snackbar.LENGTH_LONG).show();
                        else
                            Snackbar.make(parentLayout, "User not registered", Snackbar.LENGTH_LONG).show();
                    }

                };

                // Or with an email/password combination
                ref.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), authResultHandler);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, NewUser.class);
                startActivity(intent);
            }
        });




    }
}
