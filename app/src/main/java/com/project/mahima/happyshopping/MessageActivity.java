package com.project.mahima.happyshopping;

import android.content.ClipData;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> stringList;
    private ListView list;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       String username, email, message;
        stringList = new ArrayList<>();

        ArrayList<ClipData.Item> myList =  new ArrayList<ClipData.Item>();

        DbHelper helper = new DbHelper(MessageActivity.this, "HappyShoppingDatabase", null, 1);
        MyDatabase database = new MyDatabase(helper);

        list= (ListView) findViewById(R.id.listmessage);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(arrayAdapter);


        Cursor cursor= database.fetchquery();
        if(cursor.moveToFirst()){
            do {
                username=cursor.getString(cursor.getColumnIndex("Username"));
                email=cursor.getString(cursor.getColumnIndex("Email"));
                message=cursor.getString(cursor.getColumnIndex("Message"));
                stringList.add(username + "\n" + email + "\n" + message);
                Log.e("mahima","Message name: " + username);
            }while (cursor.moveToNext());
        }

        arrayAdapter.addAll(stringList);
        arrayAdapter.notifyDataSetChanged();




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
        getMenuInflater().inflate(R.menu.message, menu);
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
            Intent intent= new Intent(MessageActivity.this,MainActivity.class);
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
            Intent intent= new Intent(MessageActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString("Allow","menwear");
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_womenwear) {

            Intent intent= new Intent(MessageActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString("Allow","womenwear");
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_kidwear) {

        } else if (id == R.id.nav_mobile) {

        } else if (id == R.id.nav_laptop) {

        }
        else if (id == R.id.nav_userquery) {
            Intent intent= new Intent(MessageActivity.this,MessageActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_shipping) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
