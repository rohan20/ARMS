package com.example.rohan.arms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Login extends AppCompatActivity {

    Button bLogin;
    ProgressDialog progressDialog;
    EditText etEmail;
    EditText etPassword;
    Firebase rootRef;
    Firebase userRef;
    View parentLayout;
    SharedPreferences mSharedPreferences;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences("ARMS_USER",MODE_PRIVATE);
        if(mSharedPreferences.contains(Constant.SHARED_PREF_UID_KEY)){
            Intent intent = new Intent(this,Menu.class);
            startActivity(intent);
            this.finish();
            uid = mSharedPreferences.getString(Constant.SHARED_PREF_UID_KEY,"");
        }
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);
        rootRef = new Firebase(Constant.ROOT_URL);
        userRef = rootRef.child("users");
        bLogin = (Button)findViewById(R.id.login2);
        etEmail = (EditText)findViewById(R.id.email);
        etPassword = (EditText)findViewById(R.id.password);
        parentLayout = findViewById(R.id.view);
 //       bRegister = (Button)findViewById(R.id.registernewuser);
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
                rootRef.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        progressDialog.dismiss();
                        uid = authData.getUid();
                        SharedPreferences.Editor editor = mSharedPreferences.edit();
                        editor.putString(Constant.SHARED_PREF_UID_KEY,uid);
                        editor.commit();
                        Toast.makeText(getApplication(), "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this,Menu.class);
                        startActivity(intent);
                        Login.this.finish();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

//        bRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, NewUser.class);
//                startActivity(intent);
//            }
//        });




    }
}
