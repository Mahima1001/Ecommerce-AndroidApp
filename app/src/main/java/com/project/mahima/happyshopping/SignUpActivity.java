package com.project.mahima.happyshopping;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Patterns;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

public class SignUpActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private EditText FirstName , Address , Email , Mobile , Password ;

    private Button btnSave ;



    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent= getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        FirstName = (EditText) findViewById(R.id.firstname);
        Address = (EditText) findViewById(R.id.address);
        Email = (EditText) findViewById(R.id.email);
        Mobile = (EditText) findViewById(R.id.mobile);
        Password = (EditText) findViewById(R.id.password);

        btnSave = (Button) findViewById(R.id.btnSave);

        awesomeValidation.addValidation(this, R.id.firstname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
       // awesomeValidation.addValidation(this, R.id.mobile, "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.nameerror);


        findViewById(R.id.btnSave)
                .setOnClickListener(new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int flag = 0;
                        DbHelper helper = new DbHelper(SignUpActivity.this, "HappyShoppingDatabase", null, 1);
                        MyDatabase database = new MyDatabase(helper);


                        if (awesomeValidation.validate()) {
                            if(database.CheckForUniqueUsername(FirstName.getText().toString()).compareTo("You can use this username")==0) {
                                Toast.makeText(SignUpActivity.this, "Validation Successfull", Toast.LENGTH_LONG).show();
                                database.insert(FirstName.getText().toString(),
                                        Password.getText().toString(),
                                        Address.getText().toString(),
                                        Email.getText().toString(),
                                        Mobile.getText().toString());

                                finish();
                            }

                            else
                            {
                                Toast.makeText(SignUpActivity.this, "Username Already Used!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    });




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
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return false;

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           Intent intent= new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent= new Intent(SignUpActivity.this,ContactActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent= new Intent(SignUpActivity.this,AboutActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
