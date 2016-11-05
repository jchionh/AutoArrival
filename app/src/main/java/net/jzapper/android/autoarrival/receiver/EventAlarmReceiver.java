package net.jzapper.android.autoarrival.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.jzapper.android.autoarrival.app.MainActivity;

/**
 * Created by jchionh
 * Date: 11/5/16
 * Time: 2:13 AM
 */

public class EventAlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent notificationIntent = new Intent(MainActivity.EVENT_INTENT_FILTER);
        context.sendBroadcast(notificationIntent);
    }
}
