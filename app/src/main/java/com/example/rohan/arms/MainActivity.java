package com.example.rohan.arms;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    Button bLogin;
    ImageButton ibWallpaper;
    TextView armsFullForm;
    TextView armsShortForm;

    int i = 2;

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
//                if(i % 2 == 0)
//                {
//                    armsShortForm.setVisibility(View.VISIBLE);
//                    armsFullForm.setVisibility(View.INVISIBLE);
//                    i++;
//                }
//
//                else
//                {
//                    armsShortForm.setVisibility(View.INVISIBLE);
//                    armsFullForm.setVisibility(View.VISIBLE);
//                    i++;
//                }


                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);


            }
        });

    }

}
