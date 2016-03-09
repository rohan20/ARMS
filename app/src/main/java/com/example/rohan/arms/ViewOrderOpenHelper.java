package com.example.rohan.arms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rohan on 09-Mar-16.
 */
public class ViewOrderOpenHelper extends SQLiteOpenHelper
{

    public final static String ORDER_TABLE = "OrderTable";
    public final static String ORDER_ITEM_NAME = "OrderItemsName";
    public final static String ORDER_ITEM_PRICE = "OrderItemsPrice";
    public final static String ORDER_ITEM_QUANTITY = "CartItemsQuantity";
//    public final static String ORDER_ITEM_TIMESTAMP = "CartItemsTimeStamp";

    public ViewOrderOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, "Order Database", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
//        CREATE TABLE TableName(ItemName varchar(255), ItemPrice varchar(255), ItemQunatity varchar(255));
        db.execSQL("CREATE TABLE " + ORDER_TABLE + " ( "  + ORDER_ITEM_NAME + " varchar(255), " +
            ORDER_ITEM_PRICE + " varchar(255), " + ORDER_ITEM_QUANTITY + " varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
