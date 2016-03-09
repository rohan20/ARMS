package com.example.rohan.arms;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Menu extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Firebase rootRef,userRef,restaurantRef,menuRef;
    String uid;
    RecyclerView recyclerView;
    MenuRecyclerAdapter mAdapter;
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nameTextView = (TextView)this.findViewById(R.id.menuRestaurantNameTextView);
        sharedPreferences = getSharedPreferences("ARMS_USER", MODE_PRIVATE);
        uid = sharedPreferences.getString(Constant.SHARED_PREF_UID_KEY, "");
        rootRef = new Firebase(Constant.ROOT_URL);
        userRef = rootRef.child("users").child(uid);
        restaurantRef = userRef.child("restaurant");
        Firebase restaurantNameRef = restaurantRef.child("name");
        restaurantNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nameTextView.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                nameTextView.setText("Unable to connect");
            }
        });
        menuRef = restaurantRef.child("menu");
        recyclerView = (RecyclerView)this.findViewById(R.id.recyclerView);
        mAdapter = new MenuRecyclerAdapter(this,new MenuRecyclerAdapter.ItemClickListener() {
            @Override
            public void onClick(int position) {
                com.example.rohan.arms.MenuItem menuItem = mAdapter.getItem(position);
                Toast.makeText(getApplicationContext(), menuItem.getName(), Toast.LENGTH_LONG).show();
            }
        },
                com.example.rohan.arms.MenuItem.class,
                R.layout.row_menu_item, MenuRecyclerAdapter.MenuViewHolder.class,menuRef);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        View parentLayout = findViewById(R.id.root_view);
        Snackbar.make(parentLayout, "Log In Successful", Snackbar.LENGTH_LONG).show();
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
