package com.example.rohan.arms;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    Button bLogin;
    TextView armsFullForm;
    TextView armsShortForm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLogin = (Button)findViewById(R.id.login);
        armsFullForm = (TextView) findViewById(R.id.ARMS);
        armsShortForm = (TextView) findViewById(R.id.arms);
        armsShortForm.setVisibility(View.INVISIBLE);
        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/corbel.ttf");
        armsFullForm.setTypeface(t);
        armsShortForm.setTypeface(t);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
    }

}
