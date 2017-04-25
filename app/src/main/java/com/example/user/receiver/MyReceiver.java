package com.example.user.receiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class MyReceiver extends BroadcastReceiver {


    public MyReceiver() {
    }

    static int id = 70000;//通知編號

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("NewApi") //新版通知 (api>16)
    @Override

    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        //Toast.makeText(context, "HW4.2\n廣播已接收 : "+message, Toast.LENGTH_SHORT).show();    //測試用Toast



        String name = intent.getStringExtra("Name_Key");

        Intent newIntent = new Intent();
        newIntent.setClass(context,MainActivity.class);
        newIntent.putExtra("Name",name);//第一個參數是使用第二個參數的通關密語

        PendingIntent pi = PendingIntent.getActivity(context, 0 , newIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        //將設定好的 newIntent 導入 PendingIntent

        //震動時間宣告
        long[]  vibrate_effect = {80, 350, 100, 120, 80, 120}; //跟 LINE 一樣的頻率


        //取得系統音效
        Uri mySoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //系統預設通知音效

        //利用 Notification Builder 建立 Notification 內容
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Hello");//設定通知標題
        builder.setContentText(name);//設定通知內容
        builder.setSmallIcon(R.mipmap.ic_launcher);//設定通知小圖示
        builder.setContentIntent(pi);//設定通知點擊內容
        builder.setTicker(name);
        builder.setWhen(System.currentTimeMillis());//設定通知發送時間 (立即)
        builder.setAutoCancel(true); //設定單次通知 (點擊後消失)

        //建立 Notification 物件 (myNotify)
        Notification notify = builder.build();//將 Notification Builder 建立的 Notification 內容載入至 Notification 中

        NotificationManager notificationManager = //建立 Notification Manager 物件 (myManager)
                (NotificationManager)context.
                        getSystemService(Context.NOTIFICATION_SERVICE);//取得系統通知服務

        notificationManager.notify(id++, notify);//推送通知


    }



}
