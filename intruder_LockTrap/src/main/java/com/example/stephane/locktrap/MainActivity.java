package com.example.stephane.locktrap;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends Activity implements OnClickListener {
    private Button lock;
    private Button disable;
    private Button enable;
    private Button ActiverNotif;
    private Button DesactiverNotif;
    private Button txtService;
    private Button Galerie;
    private Button autorisation;
    private TextView Date;
    int AttemptsMaxi = 0;

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    static final int RESULT_ENABLE = 1;
    // Pour Activer Appli Admistration Appareil
    DevicePolicyManager deviceManger;
    ActivityManager activityManager;
    ComponentName compName;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    // Gerer l'activité Activer Appli Admi Appareil
    deviceManger = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
    activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
    // Ouvrir la class MyAdmin pour gerer les mauvais sur MDP
    compName = new ComponentName(this, MyAdmin.class);


    // ********** Charger la photo ************
        // Donner le lien ou est enregistré la photo
        File imgFile = new  File("/data/user/0/com.example.stephane.locktrap/app_imageDir/IMG.jpg");
        // Si le lien existe
        if(imgFile.exists()){
            // ImageView = Photo Prise
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.photo);
            myImage.setImageBitmap(myBitmap);
        }
    //******* FIN ********

    //Gestion d'un click sur les boutons
        lock =(Button)findViewById(R.id.lock);
        lock.setOnClickListener(this);

        disable = (Button)findViewById(R.id.btnDisable);
        disable.setOnClickListener(this);
        enable =(Button)findViewById(R.id.btnEnable);
        enable.setOnClickListener(this);

        ActiverNotif = (Button)findViewById(R.id.ActiverNotif);
        ActiverNotif.setOnClickListener(this);
        DesactiverNotif =(Button)findViewById(R.id.DesactiverNotif);
        DesactiverNotif.setOnClickListener(this);

        txtService = (Button)findViewById(R.id.txtService);
        txtService.setOnClickListener(this);

        Galerie =(Button)findViewById(R.id.Galerie);
        Galerie.setOnClickListener(this);

        autorisation =(Button)findViewById(R.id.autorisation);
        autorisation.setOnClickListener(this);

        Date =(TextView) findViewById(R.id.Date);

        rb1 =(RadioButton) findViewById(R.id.t1);
        rb2 =(RadioButton) findViewById(R.id.t2);
        rb3 =(RadioButton) findViewById(R.id.t3);

        SharedPreferences sp = getSharedPreferences("MyPreference", Activity.MODE_PRIVATE);
        int check = sp.getInt("tentative", 0);

        if (check ==1)
            rb1.setChecked(true);
        if (check ==2)
            rb2.setChecked(true);
        if (check ==3)
            rb3.setChecked(true);


        // ******** Mettre la Date et l'heure sous l'image ************
        // SharedPreferences ==> Sauvegarder des informations
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String restoredText = preferences.getString("date",null);
      // Si Vérifier que le le text n'est pas vide ++> (Eviter un NullPointerExeption)
        if(restoredText != null)
        {
            String DateHeure = preferences.getString("date", "No Date");
            Date.setText(DateHeure);
        }

      // Récuperer une Variable d'une Activité a l'autre (sans sauvegarde)
             /*  Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String DateHeure = extras.getString("key");
            Date.setText(DateHeure);
        }*/


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.t1:
                if (checked)
                 AttemptsMaxi = 1;
                    break;
            case R.id.t2:
                if (checked)
                    AttemptsMaxi = 2;
                    break;
            case R.id.t3:
                if (checked)
                    AttemptsMaxi = 3;
                    break;
        }

        SharedPreferences mySharedPreferences = this.getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("tentative",AttemptsMaxi);
        editor.apply();

    }


//********** Gestion des Click ********************
    @Override
    public void onClick(View v) {


        if(v == txtService)
        {
            if (txtService.getText().toString().equals("Desactiver")) {
                txtService.setText("Activer");
               // stopService(new Intent(MainActivity.this,service.class));
            } else {
                txtService.setText("Desactiver");
             //   startService(new Intent(MainActivity.this,service.class));
            }
        }

        if(v == ActiverNotif)
        {
            Toast.makeText(MainActivity.this, "Notif Activer", Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("textTitle")
                    .setContentText("textContent")
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());

            }

        if(v == DesactiverNotif)
        {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(1);
        }

        // Si click sur lock
        if(v == lock)
        {
            // Verifier que l'administration est active
            boolean active = deviceManger.isAdminActive(compName);
            // Si active vérrouiller le telephone
            if (active)
            {
                this.finish();
                deviceManger.lockNow();
            }


        }

        //Si bouton activer click
        if(v == enable)
        {
            //Démarer l'activité Appli Admin App
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "L'autorisation permet de vérouiller l'application avec mot de passe obligatoire");
            // Démarre la fonction startActivityResult ==> Activation de Appl Admin App OK
            startActivityForResult(intent, RESULT_ENABLE);
        }

        // Si désactiver click
        if(v == disable)
        {
            // Désactiver Appli Admin App
            deviceManger.removeActiveAdmin(compName);
        }

        //Si click sur Galerie
        if(v == Galerie){
            //Demarrer la nouvelle activite
            Intent i = new Intent(MainActivity.this, Gallery.class);
            startActivity(i);
        }

        // SI click sur autoriser
        if(v == autorisation)
        {
            // Ouvre un lien vers les autorisations
            //==> Normalement les autorisation sont demandées directement dans l'app quand utilisée.
            // Cependant la caméra est utilisée sans interface visible par l'utilisateur ==> activer manuellement
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
    }

    // Retourne l'information Activation de Appl Admin App OK
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_ENABLE:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(MainActivity.this, "You have enabled the Admin Device features", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Problem to enable the Admin Device features", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
    @Override
    public void onBackPressed()
    {
        finish();

    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER)
        {
            deviceManger.lockNow();
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
