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

    com.example.rohan.arms.MenuItem[] menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final GridView gridview = (GridView) findViewById(R.id.gridview);

        menuItems = new com.example.rohan.arms.MenuItem[9];

        for(int i=0; i < 9; i+=3)
        {
            menuItems[i] = new com.example.rohan.arms.MenuItem();
            menuItems[i].setName("Chicken Burger");
            menuItems[i].setImageUrl("http://40.media.tumblr.com/062e35064c093c8dff0b659eefbd7064/tumblr_n5ztctVEew1s3jg9qo1_500.png");
            menuItems[i].setPrice("Rs. 200/-");
        }

        for(int i=1; i < 9; i+=3)
        {
            menuItems[i] = new com.example.rohan.arms.MenuItem();
            menuItems[i].setName("Veg Noodles");
            menuItems[i].setImageUrl("http://www.omazoni.com/assets/user_images/slider_images/cropped/Noodles+Omazoni+1411796765.png");
            menuItems[i].setPrice("Rs. 250/-");
        }

        for(int i=2; i < 9; i+=3)
        {
            menuItems[i] = new com.example.rohan.arms.MenuItem();
            menuItems[i].setName("Paneer Tikka");
            menuItems[i].setImageUrl("http://www.omazoni.com/assets/user_images/slider_images/cropped/Paneer+Tikka+Final+1411661204.png");
            menuItems[i].setPrice("Rs. 275/-");
        }


        MenuItemArrayAdapter allItems = new MenuItemArrayAdapter(getApplicationContext(), 0, menuItems);
        gridview.setAdapter(allItems);

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
