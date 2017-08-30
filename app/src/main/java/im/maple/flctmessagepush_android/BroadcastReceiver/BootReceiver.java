package im.maple.flctmessagepush_android.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import im.maple.flctmessagepush_android.AppService.AliveService;

/**
 * Created by maple on 2017/8/30.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,AliveService.class);
        context.startService(service);
        Log.d("TAG", "开机自动服务自动启动.....");
        //启动应用，参数为需要自动启动的应用的包名
        PackageManager manager = context.getPackageManager();
        Intent currentIntent = manager.getLaunchIntentForPackage("im.maple.flctmessagepush_android");
        context.startActivity(currentIntent);
    }
}