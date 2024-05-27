package vuk.antovic.onlineshop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.time.LocalTime;

public class SaleService extends Service {
    Binder binder = null;
    Thread thread = null;

    public SaleService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true) {
                    try{
                        LocalTime now = LocalTime.now();
                        if (true) {
                            Thread.sleep(50);
                            sendNotification();
                            binder.setSale(true);
                            Thread.sleep(10000);
                            binder.setSale(false);
                            Intent finishIntent = new Intent("com.example.FINISH_ACTIVITY");
                            sendBroadcast(finishIntent);
                            sendEndNotification();
                            Thread.sleep(5000000);
                        }
                        Thread.sleep(1000);
                    }catch (InterruptedException | RemoteException e){
                        Thread.currentThread().interrupt();
                    }

                }
            }
        });
    }

    public void sendNotification() throws RemoteException {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create notification channel
        NotificationChannel channel = new NotificationChannel("1",
                "myChannel",
                NotificationManager.IMPORTANCE_HIGH); // Set importance to high for the notification to pop up
        channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
        mNotificationManager.createNotificationChannel(channel);

        // Create intent to launch HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("sale", binder.getSale());
        intent.putExtra("username", binder.getUsername());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Build the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.mipmap.ic_launcher) // Notification icon
                .setContentTitle("The sale has begun!") // Title for notification
                .setContentText("Click to see more.") // Message for notification
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Set notification priority to high
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Set notification visibility to public
                .setAutoCancel(true) // Clear notification after click
                .setContentIntent(pendingIntent);

        // Notify
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void sendEndNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("1",
                "myChannel",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
        mNotificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("SALE HAS ENDED!") // title for notification
                .setContentText("Unfortunately, today's sale has ended!")// message for notification
                .setAutoCancel(true); // clear notification after click
        mNotificationManager.notify(0, mBuilder.build());
    }


    @Override
    public IBinder onBind(Intent intent) {
        if(binder == null)
        {
            binder = new Binder();
        }
        if (!thread.isAlive()) {
            thread.start();
        }

        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        try {
            binder.setSale(false);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return super.onUnbind(intent);
    }
}