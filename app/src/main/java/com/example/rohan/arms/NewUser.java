package com.example.rohan.arms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class NewUser extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    Button bRegister;
    Button bCancel;

    ProgressDialog progressDialog;
    View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        parentLayout = findViewById(R.id.newUserLayout);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase("https://popping-inferno-2396.firebaseio.com/");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Registering new user...");
        progressDialog.setMessage("This may take a while...");

        etEmail = (EditText)findViewById(R.id.newEmail);
        etPassword = (EditText)findViewById(R.id.newPassword);
        etConfirmPassword = (EditText)findViewById(R.id.newConfirmPassword);
        bRegister = (Button)findViewById(R.id.register);
        bCancel = (Button)findViewById(R.id.cancel);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    etEmail.setError("Enter Email");
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
                    etPassword.setError("Enter Password");
                    return;
                } else if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
                    etConfirmPassword.setError("Enter Password");
                    return;
                } else if (!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))) {
                    etPassword.setError("Passwords do not match");
                    etConfirmPassword.setError("Passwords do not match");
                    Snackbar.make(parentLayout, "Passwords do not match", Snackbar.LENGTH_SHORT).show();
                    return;
                } else {

                    progressDialog.show();

                    ref.createUser(etEmail.getText().toString(), etPassword.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> stringObjectMap) {
                            System.out.println("Successfully created user account with uid: " + stringObjectMap.get("uid"));
                            Intent j = new Intent(NewUser.this, Menu.class);
                            progressDialog.dismiss();
                            startActivity(j);
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {

                            Snackbar.make(parentLayout, "Unable to register right now.", Snackbar.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(NewUser.this, Login.class);
                startActivity(i);
            }
        });




    }
}
