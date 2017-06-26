package com.project.mahima.happyshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_ALLOW = "Allow" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= (Button) findViewById(R.id.btn_login);

                btn.setOnClickListener(new View
                        .OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                                ProgressDialog.STYLE_SPINNER);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Authenticating...");
                        progressDialog.show();

                        DbHelper helper = new DbHelper(MainActivity.this, "HappyShoppingDatabase", null, 1);
                        MyDatabase database = new MyDatabase(helper);

                        if(getUsername().equals(""))
                        {
                            progressDialog.dismiss();
                            createAlertDialog("Username is must");
                        }

                        else if(getPassWord().equals(""))
                        {
                            progressDialog.dismiss();
                            createAlertDialog("Password is must");
                        }
                        else if(getUsername().equals("admin") && getPassWord().equals("admin@123")){
                            Intent intent= new Intent(MainActivity.this,AdminActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putString(KEY_ALLOW,getUsername());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        else if(database.CheckLogin(getUsername(),getPassWord()).equals("Deny Login")){
                            progressDialog.dismiss();
                            createAlertDialog("Username or Password is incorrect");
                        }
                        else{
                            progressDialog.dismiss();
                            Intent intent= new Intent(MainActivity.this,UserActivity.class);
                            Bundle bundle= new Bundle();
                            bundle.putString(KEY_ALLOW,getUsername());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                });

        findViewById(R.id.txt_clickforsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        DbHelper helper = new DbHelper(MainActivity.this, "HappyShoppingDatabase", null, 1);
        MyDatabase database = new MyDatabase(helper);
        database.insert("mah","mah","ghff jkj ij","mah@gmail.com","mah mah");

    }

    public String getUsername(){
        EditText Username= (EditText) findViewById(R.id.edt_username);
        return Username.getText().toString();
    }

    public String getPassWord(){
        EditText PassWord= (EditText) findViewById(R.id.edt_password);
        return PassWord.getText().toString();
    }


   /* public void msg(String message){
        Toast.makeText(this,"Error->" + message,Toast.LENGTH_SHORT).show();
    }*/

    private final void createAlertDialog(String message){

        MyDialogFragment dialogFragment =
                new MyDialogFragment();
        dialogFragment.MessageToDisplay(message);
        dialogFragment.show(getSupportFragmentManager(), MyDialogFragment.TAG_ALERT);

    }
}
