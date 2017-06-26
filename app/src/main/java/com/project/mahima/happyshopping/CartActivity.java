package com.project.mahima.happyshopping;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.ItemAdapter;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<Item> itemList;
    private ListView listView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvCartList);
        itemAdapter = new ItemAdapter(getApplicationContext());
        itemAdapter.setIsCart(true);
        listView.setAdapter(itemAdapter);

        MyDatabase database = new MyDatabase(new DbHelper(getApplicationContext(),"HappyShoppingDatabase", null, 1));
        Cursor cursor = database.getCart(UserActivity.Login_User_Name);
        Log.e("Mahima","cart count " + String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()){
            itemList.clear();
            do{
                Log.e("mahima",cursor.toString());
                Item item1 = new Item(

                        cursor.getString(cursor.getColumnIndex("imagename")),
                        cursor.getString(cursor.getColumnIndex("imagedesc")),
                        cursor.getString(cursor.getColumnIndex("cost")),
                        cursor.getString(cursor.getColumnIndex("image")),
                        cursor.getString(cursor.getColumnIndex("imagetype"))
                );
                Log.e("mahima",item1.getName());
                itemList.add(item1);
            }while (cursor.moveToNext());
        }

        itemAdapter.setItemList(itemList);
        itemAdapter.notifyDataSetChanged();
    }
}
