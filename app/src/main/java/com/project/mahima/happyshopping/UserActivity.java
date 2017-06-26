package com.project.mahima.happyshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.ItemAdapter;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Item> itemList;
    private ListView listView;
    private ItemAdapter itemAdapter;
    public static String Login_User_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
        String name= bundle.getString("Allow");

        Login_User_Name  = name;

        itemList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvItemList);
        itemAdapter = new ItemAdapter(getApplicationContext());
        listView.setAdapter(itemAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent =  new Intent(UserActivity.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menwear) {
            MyDatabase database = new MyDatabase(new DbHelper(getApplicationContext(),"HappyShoppingDatabase", null, 1));
            Cursor cursor = database.getAllImage("menwear");
            Log.e("Mahima","Menwear: " + String.valueOf(cursor.getCount()));
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


        } else if (id == R.id.nav_womenwear) {

            MyDatabase database = new MyDatabase(new DbHelper(getApplicationContext(),"HappyShoppingDatabase", null, 1));
            Cursor cursor = database.getAllImage("womenwear");
            Log.e("Mahima","Womenwear: " + String.valueOf(cursor.getCount()));
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


        } else if (id == R.id.nav_kidwear) {

        } else if (id == R.id.nav_mobile) {

        } else if (id == R.id.nav_laptop) {

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(UserActivity.this,CartActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_contact) {
            Intent intent = new Intent(UserActivity.this,ContactActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_about) {

            Intent intent = new Intent(UserActivity.this,AboutActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
