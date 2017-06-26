package com.project.mahima.happyshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getIntent();
        TextView text= (TextView) findViewById(R.id.text);
        text.setText("Shopping is easy");
    }
}
