package im.maple.flctmessagepush_android.AppService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import im.maple.flctmessagepush_android.MainActivity;

/**
 * Created by maple on 2017/8/30.
 */

public class AliveService extends Service {
    public AliveService() {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setWhen(System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,notificationIntent , 0);
        builder.setContentIntent(contentIntent);
        Notification notification = builder.build();
        startForeground(1, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getBooleanExtra("isStop", false)) {
            recover();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private void recover() {
        stopForeground(true);
    }

    @Override
    public void onDestroy() {
        Log.e("Myservice", "onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}