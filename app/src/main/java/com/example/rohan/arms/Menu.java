package com.example.rohan.arms;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        View parentLayout = findViewById(R.id.root_view);

        Typeface t = Typeface.createFromAsset(getAssets(), "fonts/corbel.ttf");
        TextView tv = (TextView)findViewById(R.id.clickOnItemToAddToOrder);
        tv.setTypeface(t);

        Snackbar.make(parentLayout, "Log In Successful", Snackbar.LENGTH_LONG).show();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                AlertDialog.Builder b = new AlertDialog.Builder(Menu.this);
                b.setTitle("Add to order?");
                b.setMessage("Price: Rs.200/-");

                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(view, "Item added to order", Snackbar.LENGTH_SHORT).show();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                b.create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.viewOrder)
        {
            Intent i = new Intent(this, ViewOrder.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
