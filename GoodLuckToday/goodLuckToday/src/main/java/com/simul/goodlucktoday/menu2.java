package com.simul.goodlucktoday;

/**
 * Created by stephane on 26/11/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class menu2 extends Activity {

    public Button re = null;
    public Button roul = null;
    public Button chance = null;
    public Button bon = null;
    public Button carte = null;
    public Button pof = null;
    int ch = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu2);

        re =(Button)findViewById(R.id.re);
        roul =(Button)findViewById(R.id.roul);
        bon =(Button)findViewById(R.id.bon);
        chance =(Button)findViewById(R.id.chance);
        carte =(Button)findViewById(R.id.carte);
        pof =(Button)findViewById(R.id.pof);

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, menu.class);

                startActivity(secondeActivite);

            }
        });

        roul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, jeux5.class);
                secondeActivite.putExtra("a", ch);
                startActivity(secondeActivite);

            }
        });
        chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, jeux4.class);
                secondeActivite.putExtra("a", ch);
                startActivity(secondeActivite);

            }
        });
        bon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, jeux3.class);
                secondeActivite.putExtra("a", ch);
                startActivity(secondeActivite);

            }
        });
        carte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, jeux2.class);
                secondeActivite.putExtra("a", ch);
                startActivity(secondeActivite);

            }
        });
        pof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondeActivite = new Intent(menu2.this, jeux1.class);
                secondeActivite.putExtra("a", ch);
                startActivity(secondeActivite);

            }
        });
    }
}
