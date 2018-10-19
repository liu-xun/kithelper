package baidumapsdk.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import baidumapsdk.demo.SplashActivity;

/**
 * Created by ASUS on 2018/10/11.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    public static final String action_boot="android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(action_boot)){
            Toast.makeText(context,"收到开机广播", Toast.LENGTH_SHORT).show();
            Intent ootStartIntent=new Intent(context, SplashActivity.class);
            ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(ootStartIntent);
        }
    }
}
