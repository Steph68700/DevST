package com.example.stephane.locktrap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class Galerie extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galerie);


                ImageView im1 = (ImageView) findViewById(R.id.im1);
                ImageView im2 = (ImageView) findViewById(R.id.im2);
                ImageView im3 = (ImageView) findViewById(R.id.im3);
                ImageView im4 = (ImageView) findViewById(R.id.im4);
                ImageView im5 = (ImageView) findViewById(R.id.im5);
                ImageView im6 = (ImageView) findViewById(R.id.im6);
                ImageView im7 = (ImageView) findViewById(R.id.im7);
                ImageView im8 = (ImageView) findViewById(R.id.im8);
                ImageView im9 = (ImageView) findViewById(R.id.im9);
                ImageView im10 = (ImageView) findViewById(R.id.im10);
                ImageView im11 = (ImageView) findViewById(R.id.im11);
                ImageView im12 = (ImageView) findViewById(R.id.im12);

                /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                int count = preferences.getInt("count_key",1);
                int count1=count-1;*/
                File imgFile1 = new File("/data/user/0/com.example.stephane.locktrap/app_imageDir/IMG.jpg");
                File imgFile2 = new File("/data/user/0/com.example.stephane.locktrap/app_imageDir/IMG.jpg");

                if (imgFile1.exists())
                {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
                    im1.setImageBitmap(myBitmap);

                }

                if (imgFile2.exists())
                {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
                    im2.setImageBitmap(myBitmap);

                }
    }
}