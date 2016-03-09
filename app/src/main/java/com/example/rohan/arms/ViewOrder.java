package com.example.rohan.arms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity
{
    ListView lv;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        int totalOrder = 0;

        lv = (ListView)findViewById(R.id.finalOrderList);
        tv = (TextView)findViewById(R.id.total);

        ArrayList<MenuItem> allOrderedItems = (ArrayList<MenuItem>) getIntent().getSerializableExtra("OrderedItemsArray");

        OrderedItemArrayAdapter orderedItems = new OrderedItemArrayAdapter(getApplicationContext(), R.layout.ordered_item_layout, allOrderedItems);
        lv.setAdapter(orderedItems);

        for(int i = 0; i < allOrderedItems.size(); i++)
        {
            totalOrder += Integer.parseInt(allOrderedItems.get(i).getPrice());
        }

        tv.setText("Total Order Value: Rs. " + totalOrder + "/-");

    }
}
