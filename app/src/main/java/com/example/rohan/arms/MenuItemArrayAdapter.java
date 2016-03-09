package com.example.rohan.arms;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Rohan on 09-Mar-16.
 */
public class MenuItemArrayAdapter extends ArrayAdapter
{

    Context mContext;

    public MenuItemArrayAdapter(Context context, int resource, MenuItem[] objects)
    {
        super(context, resource, objects);
        mContext = context;
    }

    public class MenuItemViewHolder
    {
        TextView tvItemName;
        TextView tvItemPrice;
        ImageView ivItemImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.menu_item, null);

            MenuItemViewHolder vh = new MenuItemViewHolder();

            vh.tvItemName = (TextView)convertView.findViewById(R.id.name);
            vh.tvItemName.setTextColor(Color.BLACK);
            vh.tvItemPrice = (TextView)convertView.findViewById(R.id.price);
            vh.tvItemPrice.setTextColor(Color.BLACK);
            vh.ivItemImage = (ImageView)convertView.findViewById(R.id.image);

            convertView.setTag(vh);
        }

            MenuItemViewHolder vh = (MenuItemViewHolder)convertView.getTag();

            MenuItem item = (MenuItem)getItem(position);
            vh.tvItemName.setText(item.getName());
            vh.tvItemName.setTextColor(Color.BLACK);
            vh.tvItemPrice.setText(item.getPrice());
            vh.tvItemPrice.setTextColor(Color.BLACK);
            Picasso.with(mContext).load(item.getImageUrl()).into(vh.ivItemImage);

        return convertView;
    }
}
