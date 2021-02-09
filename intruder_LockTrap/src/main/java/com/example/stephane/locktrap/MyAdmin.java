package com.example.stephane.locktrap;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MyAdmin extends DeviceAdminReceiver{


    private Context mContext;


    static SharedPreferences getSamplePreferences(Context context) {
        return context.getSharedPreferences(DeviceAdminReceiver.class.getName(), 0);
    }

    static String PREF_PASSWORD_QUALITY = "password_quality";
    static String PREF_PASSWORD_LENGTH = "password_length";
    static String PREF_MAX_FAILED_PW = "max_failed_pw";


    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: enabled");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "This is an optional message to warn the user about disabling.";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Samlple Device Admin: disabed");
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: pw changed");
    }

    // Si MDP Failed
    public void onPasswordFailed(Context context, Intent intent) {
        // Gestion des mots de passe
        DevicePolicyManager policyManager = (DevicePolicyManager)context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        // Si mdp différents de null ==> Spécifications renseignées (4 chiffres,....)
        if(policyManager != null){
            // Tentative = Nbr de mdp failed
            int attempts = policyManager.getCurrentFailedPasswordAttempts();

            this.mContext = context;
            SharedPreferences sp = mContext.getSharedPreferences("MyPreference", Activity.MODE_PRIVATE);
            int attemptsMax = sp.getInt("tentative", 0);

            //Si tentative >= attemptsMax
            if (attempts >= attemptsMax ) {
            // Start Camera
                Intent i = new Intent(context, CameraView.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        }
    }

    //Si MDP succedded
    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: pw succeeded");
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

}