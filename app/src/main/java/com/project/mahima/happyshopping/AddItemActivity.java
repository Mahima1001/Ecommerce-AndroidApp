package com.project.mahima.happyshopping;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String UPLOAD_IMAGE = "image";
    public static final String UPLOAD_IMAGENAME = "imagename";
    public static final String UPLOAD_USER = "user";
    public static final String UPLOAD_ID = "id";
    public static final String TAG = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonViewUpload;
    private ImageView imageView;
    public static EditText imagename;
    public static  EditText cost;
    public static  EditText desc;
    String type;

    private Bitmap bitmap;

    private Uri filePath;

    public static final String KEY_ALLOW_ADMIN = "Allow" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent= getIntent();
        Bundle bundle= intent.getExtras();
         type= bundle.getString("Allow");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        imagename = (EditText) findViewById(R.id.editTextImagename);
        cost= (EditText) findViewById(R.id.editTextCost);
        desc= (EditText) findViewById(R.id.editTextDesc);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper helper = new DbHelper(AddItemActivity.this, "HappyShoppingDatabase", null, 1);
                MyDatabase database = new MyDatabase(helper);

                //ProgressDialog loading;
                String imgname= AddItemActivity.imagename.getText().toString();
                String desc= AddItemActivity.desc.getText().toString();
                String cost= AddItemActivity.cost.getText().toString();

                String uploadImage = getStringImage(bitmap);
                database.insertimage(imgname,uploadImage,cost,type,desc);

            }
        });


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
        getMenuInflater().inflate(R.menu.add_item, menu);
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
            Intent intent = new Intent(AddItemActivity.this,MainActivity.class);
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
            Intent intent= new Intent(AddItemActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString(KEY_ALLOW_ADMIN,"menwear");
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_womenwear) {

            Intent intent= new Intent(AddItemActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString(KEY_ALLOW_ADMIN,"womenwear");
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_kidwear) {

            Intent intent= new Intent(AddItemActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString(KEY_ALLOW_ADMIN,"kidwear");
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_mobile) {

            Intent intent= new Intent(AddItemActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString(KEY_ALLOW_ADMIN,"mobile");
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_laptop) {

            Intent intent= new Intent(AddItemActivity.this,AddItemActivity.class);
            Bundle bundle= new Bundle();
            bundle.putString(KEY_ALLOW_ADMIN,"laptop");
            intent.putExtras(bundle);
            startActivity(intent);

        }
        else if (id == R.id.nav_userquery) {

            Intent intent= new Intent(AddItemActivity.this,MessageActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_shipping) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
