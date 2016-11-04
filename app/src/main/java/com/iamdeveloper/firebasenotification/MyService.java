package com.iamdeveloper.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by IamDeveloper on 10/27/2016.
 */
public class MyService extends FirebaseMessagingService {
    private static int id = 0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("Message", "new message");
        String remote = remoteMessage.getNotification().getBody();
        if(!remote.isEmpty()){
            notification(remote);
        }

    }

    private void notification(String message){
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentTitle("Firebase Notification");
        notification.setContentText(message);



        Intent i = new Intent(this,MainActivity.class);
        TaskStackBuilder builder = TaskStackBuilder.create(this);
        builder.addParentStack(MainActivity.class);
        builder.addNextIntent(i);

        PendingIntent pIntent = builder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);

        notification.setContentIntent(pIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ++id;
        Log.d("ID",id+"");
        manager.notify(id,notification.build());
    }
}
