package com.example.user.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class MyReceiver extends BroadcastReceiver {


    public MyReceiver() {
    }

    static int id = 70000;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override

    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        String name = intent.getStringExtra("Name");

        Intent newIntent = new Intent();
        newIntent.setClass(context,MainActivity.class);
        newIntent.putExtra("Name",name);

        PendingIntent pi = PendingIntent.getActivity(context, 0 , newIntent , PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Hello");
        builder.setContentText(name);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setTicker(name);
        builder.setWhen(System.currentTimeMillis());
        Notification notify = builder.build();

        NotificationManager notificationManager =
                (NotificationManager)context.
                        getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id++, notify);


    }



}
