package org.qtproject.example.hangman;

import org.qtproject.qt5.android.bindings.QtApplication;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ExternalReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        String regId = intent.getExtras().getString("registration_id");
        if(regId != null && !regId.equals("")) {
      /* Do what ever you want with the regId eg. send it to your server */
        }
        Log.d(QtApplication.QtTAG, "regId: " + regId);

        if(intent!=null){
            Bundle extras = intent.getExtras();
//            if(!MainActivity.inBackground){
//                MessageReceivingService.sendToApp(extras, context);
//            }
//            else{
                MessageReceivingService.notify(extras, context);
//                MessageReceivingService.postNotification(new Intent(context, HangmanActivity.class), context);
//            }
        }
    }
}

