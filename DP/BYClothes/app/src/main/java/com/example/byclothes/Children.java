package com.example.byclothes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Children extends AppCompatActivity {

    ImageView ca,cb,cc,cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);

        ca = findViewById(R.id.c_dress_A);
        cb = findViewById(R.id.c_dress_B);
        cc = findViewById(R.id.c_dress_C);
        cd = findViewById(R.id.c_dress_D);

    }

    public void ca_pay()
    {
        Intent payIntent = new Intent(Children.this,Payment.class);
        startActivity(payIntent);
    }

    public void cb_pay()
    {
        Intent payIntent = new Intent(this,Payment.class);
        startActivity(payIntent);
    }


    public void cc_pay()
    {
        Intent payIntent = new Intent(this,Payment.class);
        startActivity(payIntent);
    }


    public void cd_pay()
    {
        Intent payIntent = new Intent(this,Payment.class);
        startActivity(payIntent);
    }
}
