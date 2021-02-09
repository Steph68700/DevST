package com.example.stephane.locktrap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootUpReceiver extends BroadcastReceiver {

    //Detecter un reboot et lancer le serice
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, service.class);
        context.startService(service);
    }

}