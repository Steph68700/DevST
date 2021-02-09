package com.example.stephane.locktrap;

import android.app.ActivityManager;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
public class service extends Service {

    // IMPOSSIBLE DE FAIRE FONCTIONNER L APPLICATION EN TACHE DE FOND PAR DETECTION DU BOUTON POWER *******
    // A PARTIR DE ANDROID 5.0 ON PEUT DETECTER LES TOUCHE VOLUME EN TACHE DE FOND
    // SINON POSSIBILITE DE MODIFIER DIRECTEMENT LE KERNEL MAIS TRAVAIL > 1 SEMAINE
    // (Voir si possible avec la Barre des notifications)

    DevicePolicyManager deviceManger;
    ActivityManager activityManager;
    ComponentName compName;

    public void onCreate(){
        super.onCreate();

        deviceManger = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        // Ouvrir la class MyAdmin pour gerer les mauvais sur MDP
        compName = new ComponentName(this, MyAdmin.class);

        //si detect a key event power ==> Impossible dans un service
        deviceManger.lockNow();

    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();


        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
    }


    // IMPOSSIBLE DE FAIRE FONCTIONNER L APPLICATION EN TACHE DE FOND PAR DETECTION DU BOUTON POWER *******
    // A PARTIR DE ANDROID 5.0 ON PEUT DETECTER LES TOUCHE VOLUME EN TACHE DE FOND
    // SINON POSSIBILITE DE MODIFIER DIRECTEMENT LE KERNEL MAIS TRAVAIL > 1 SEMAINE
    // (Voir si possible avec la Barre des notification)


  /*  public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER)
        {
            deviceManger.lockNow();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

}