package com.example.rohan.arms;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rohan on 10-Mar-16.
 */
public class OrderedItemArrayAdapter extends ArrayAdapter
{

    Context mContext;

    public OrderedItemArrayAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);
        mContext = context;
    }

    public class OrderedItemViewHolder
    {
        TextView tvItemName;
        TextView tvItemPrice;
        TextView tvItemQuantity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.ordered_item_layout, null);

            OrderedItemViewHolder vh = new OrderedItemViewHolder();

            vh.tvItemName = (TextView)convertView.findViewById(R.id.name);
            vh.tvItemName.setTextColor(Color.BLACK);
            vh.tvItemPrice = (TextView)convertView.findViewById(R.id.price);
            vh.tvItemPrice.setTextColor(Color.BLACK);
            vh.tvItemQuantity = (TextView)convertView.findViewById(R.id.quantity);
            vh.tvItemQuantity.setTextColor(Color.BLACK);

            convertView.setTag(vh);
        }

        OrderedItemViewHolder vh = (OrderedItemViewHolder)convertView.getTag();

        MenuItem item = (MenuItem)getItem(position);
        vh.tvItemName.setText(item.getName());
        vh.tvItemName.setTextColor(Color.BLACK);
        vh.tvItemPrice.setText("Rs. " + item.getPrice() + "/-");
        vh.tvItemPrice.setTextColor(Color.BLACK);
        vh.tvItemQuantity.setText("x1");
        vh.tvItemQuantity.setTextColor(Color.BLACK);

        return convertView;
    }

}

