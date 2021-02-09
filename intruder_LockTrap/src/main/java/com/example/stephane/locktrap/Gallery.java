package com.example.stephane.locktrap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Gallery extends Activity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        ImageView im1 = (ImageView) findViewById(R.id.im1);
        txt =(TextView) findViewById(R.id.txt);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String restoredText = preferences.getString("date",null);
        // Si Vérifier que le le text n'est pas vide ++> (Eviter un NullPointerExeption)
        if(restoredText != null)
        {
            String DateHeure = preferences.getString("date", "No Date");
            txt.setText(DateHeure);
        }

        File imgFile1 = new File("/data/user/0/com.example.stephane.locktrap/app_imageDir/IMG.jpg");

        if (imgFile1.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
            im1.setImageBitmap(myBitmap);

        }

    }
}
