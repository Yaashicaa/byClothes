package com.example.byclothes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeProduct extends AppCompatActivity {

    ViewFlipper viewFlipper;
    ImageView menImg,womenImg,childImg,fancyImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_product);

        viewFlipper = findViewById(R.id.flipper_IMG_ID);

        int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

        menImg = findViewById(R.id.men_IMG_ID);
        womenImg = findViewById(R.id.women_IMG_ID);
        childImg = findViewById(R.id.children_IMG_ID);
        fancyImg = findViewById(R.id.fancy_IMG_ID);

        for(int i=0; i<images.length; i++)
        {
            autoFlipper(images[i]);
        }
    }

    public void autoFlipper(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right
        );

    }

    public void displayMen(View v)
    {
        Intent menIntent = new Intent(HomeProduct.this,Men.class);
        startActivity(menIntent);
    }

    public void displayWomen(View v)
    {
        Intent womenIntent = new Intent(HomeProduct.this,Women.class);
        startActivity(womenIntent);
    }

    public void displayChildren(View v)
    {
        Intent childrenIntent = new Intent(HomeProduct.this,Children.class);
        startActivity(childrenIntent);
    }

    public void displayFancy(View v)
    {
        Intent fancyIntent = new Intent(HomeProduct.this,Fancy.class);
        startActivity(fancyIntent);
    }
}
