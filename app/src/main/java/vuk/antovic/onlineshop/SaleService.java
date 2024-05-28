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


public class SaleService extends Service {
    static Binder binder = null;
    static Thread thread = null;

    public SaleService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        if(thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            // add condition for starting a sale
                            if (true) {
                                Thread.sleep(50);
                                binder.setSale(true);
                                sendNotification();
                                Thread.sleep(10000);
                                binder.setSale(false);
                                Intent finishIntent = new Intent("com.example.FINISH_ACTIVITY");
                                sendBroadcast(finishIntent);
                                sendEndNotification();
                                Thread.sleep(10000);
                            }
                            Thread.sleep(1000);
                        } catch (InterruptedException | RemoteException e) {
                            Thread.currentThread().interrupt();
                        }

                    }
                }
            });
        }
    }

    public void sendNotification() throws RemoteException {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

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
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Build the notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("The sale has begun!")
                .setContentText("Click to see more.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Notify
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void sendEndNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("1",
                "myChannel",
                NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
        mNotificationManager.createNotificationChannel(channel);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("SALE HAS ENDED!")
                .setContentText("Unfortunately, today's sale has ended!")
                .setAutoCancel(true);
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
        return super.onUnbind(intent);
    }
}