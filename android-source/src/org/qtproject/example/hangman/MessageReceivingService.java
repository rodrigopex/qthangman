package org.qtproject.example.hangman;

import java.io.IOException;

import org.qtproject.qt5.android.bindings.QtApplication;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
//import android.preference.PreferenceManager;
//import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/*
 * This service is designed to run in the background and receive messages from gcm. If the app is in the foreground
 * when a message is received, it will immediately be posted. If the app is not in the foreground, the message will be saved
 * and a notification is posted to the NotificationManager.
 */
public class MessageReceivingService extends Service{
    private GoogleCloudMessaging gcm;
        private static NotificationManager m_notificationManager;
        private static Notification.Builder m_builder;
    //    public static SharedPreferences savedValues;

//        public static void sendToApp(Bundle extras, Context context){
//            Intent newIntent = new Intent();
//            newIntent.setClass(context, HangmanActivity.class);
//            newIntent.putExtras(extras);
//            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(newIntent);
//        }

        public void onCreate(){
            super.onCreate();
//            m_instance = this;
            Log.e(QtApplication.QtTAG, "onCreate:CRIANDO O SERVICO!");
    //        final String preferences = getString(R.string.preferences);
    //        savedValues = getSharedPreferences(preferences, Context.MODE_PRIVATE);
            // In later versions multi_process is no longer the default
    //        if(VERSION.SDK_INT >  9){
    //            savedValues = getSharedPreferences(preferences, Context.MODE_MULTI_PROCESS);
    //        }
            gcm = GoogleCloudMessaging.getInstance(getBaseContext());
    //        SharedPreferences savedValues = PreferenceManager.getDefaultSharedPreferences(this);
    //        if(savedValues.getBoolean(getString(R.string.first_launch), true)){
            register();
    //            SharedPreferences.Editor editor = savedValues.edit();
    //            editor.putBoolean(getString(R.string.first_launch), false);
    //            editor.commit();
    //        }
            // Let AndroidMobilePushApp know we have just initialized and there may be stored messages
            //sendToApp(new Bundle(), this);
        }

//        protected static void saveToLog(Bundle extras, Context context){
//            Log.e(QtApplication.QtTAG, "saveToLog::Funcionnou!!!!!!!!!!!!!!!!!!!");
//            HangmanActivity.vowelBought('F');
//            notify("Vai que vai!");
    //        SharedPreferences.Editor editor=savedValues.edit();
    //        String numOfMissedMessages = context.getString(R.string.num_of_missed_messages);
    //        int linesOfMessageCount = 0;
    //        for(String key : extras.keySet()){
    //            String line = String.format("%s=%s", key, extras.getString(key));
    //            editor.putString("MessageLine" + linesOfMessageCount, line);
    //            linesOfMessageCount++;
    //        }
    //        editor.putInt(context.getString(R.string.lines_of_message_count), linesOfMessageCount);
    //        editor.putInt(context.getString(R.string.lines_of_message_count), linesOfMessageCount);
    //        editor.putInt(numOfMissedMessages, savedValues.getInt(numOfMissedMessages, 0) + 1);
    //        editor.commit();
//            postNotification(new Intent(context, MainActivity.class), context);
//        }

        public static void notify(Bundle extras, Context ctx)
        {
            Log.e(QtApplication.QtTAG, "saveToLog::FORA!!!!!!!!!!!!!!!!!!!");
            if (m_notificationManager == null) {
                Log.e(QtApplication.QtTAG, "saveToLog::DENTRO!!!!!!!!!!!!!!!!!!!");
                m_notificationManager = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
                m_builder = new Notification.Builder(ctx);
//                m_builder.setSmallIcon(R.drawable.icon);
                m_builder.setSmallIcon(0);
                m_builder.setContentTitle("A message from Qt!");
            }

            m_builder.setContentText("testando");
            m_notificationManager.notify(1, m_builder.build());
        }

//        public static void postNotification(Intent intentAction, Context context){
//            final NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentAction, Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL);
//            final Notification notification = new NotificationCompat.Builder(context)
//                    .setContentTitle("Message Received!")
//                    .setContentText("")
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true)
//                    .getNotification();

//            mNotificationManager.notify("1337", notification);
//        }

        private void register() {
            new AsyncTask(){
                protected Object doInBackground(final Object... params) {
                    String token;
                    try {
                        token = gcm.register("550622278555");
                        Log.i("registrationId", token);
                    }
                    catch (IOException e) {
                        Log.i("Registration Error", e.getMessage());
                    }
                    return true;
                }
            }.execute(null, null, null);
        }

        public IBinder onBind(Intent arg0) {
            return null;
        }

}
