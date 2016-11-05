package net.jzapper.android.autoarrival.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import net.jzapper.android.autoarrival.R;
import net.jzapper.android.autoarrival.event_list.EventAdapter;
import net.jzapper.android.autoarrival.receiver.EventAlarmReceiver;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EVENT_INTENT_FILTER =
            "net.jzapper.android.autoarrival.app.EVENT_INTENT_FILTER";

    @Nullable
    private EventAdapter eventAdapter;

    @NonNull
    private EventLogUpdateReceiver eventLogUpdateReceiver = new EventLogUpdateReceiver();

    @Nullable
    PendingIntent alarmIntent;

    private long previousTriggeredMs = 0;
    private long startMs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventAdapter = new EventAdapter(this);

        // now find our listview
        ListView listView = (ListView) findViewById(R.id.event_list_view);
        listView.setAdapter(eventAdapter);

        Intent intent = new Intent(this, EventAlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(eventLogUpdateReceiver, new IntentFilter(EVENT_INTENT_FILTER));
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(eventLogUpdateReceiver);
    }

    public void onStartEvents(View view)
    {
        Log.d(TAG, "Start Alarm");

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                5 * 1000,
                5 * 1000,
                alarmIntent);

        previousTriggeredMs = SystemClock.elapsedRealtime();
        startMs = previousTriggeredMs;

        eventAdapter.addFirst("Started generating events.");
    }

    public void onStopEvents(View view) {
        Log.d(TAG, "Cancel Alarm");

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.cancel(alarmIntent);

        eventAdapter.addFirst("Stopped generating events.");
    }

    /**
     * Receiver that gets an event from the alarm and update the UI
     */
    class EventLogUpdateReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Log.d(TAG, "ALARM");
            long now = SystemClock.elapsedRealtime();
            long elapsed = (now - startMs) / 1000;
            long interval = (now - previousTriggeredMs) / 1000;
            previousTriggeredMs = now;
            eventAdapter.addFirst("Elapsed: " + elapsed + "s, Interval: " + interval + "s");
        }
    }
}
