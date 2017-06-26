package com.project.mahima.happyshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.project.mahima.happyshopping.com.DatabasePackage.DbHelper;
import com.project.mahima.happyshopping.com.DatabasePackage.MyDatabase;

public class ContactActivity extends AppCompatActivity {

    EditText name , email, message;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name= (EditText) findViewById(R.id.queryname);
        email= (EditText) findViewById(R.id.queryemail);
        message= (EditText) findViewById(R.id.querymsg);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.queryname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.queryemail, Patterns.EMAIL_ADDRESS, R.string.nameerror);

         findViewById(R.id.querysend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper helper = new DbHelper(ContactActivity.this, "HappyShoppingDatabase", null, 1);
                MyDatabase database = new MyDatabase(helper);

                if (awesomeValidation.validate()) {

                    database.insertquery(name.getText().toString(),
                            email.getText().toString(),
                            message.getText().toString()
                           );

                    Toast.makeText(ContactActivity.this, "Message sent from " + name.getText().toString(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
