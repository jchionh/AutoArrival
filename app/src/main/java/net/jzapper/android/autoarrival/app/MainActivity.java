package net.jzapper.android.autoarrival.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import net.jzapper.android.autoarrival.R;
import net.jzapper.android.autoarrival.event_list.EventAdapter;

public class MainActivity extends AppCompatActivity
{
    @Nullable
    private EventAdapter eventAdapter;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventAdapter = new EventAdapter(this);

        // now find our listview
        ListView listView = (ListView) findViewById(R.id.event_list_view);
        listView.setAdapter(eventAdapter);
    }

    public void onStartEvents(View view)
    {
        eventAdapter.addFirst(String.valueOf(count));
        ++count;
    }
}
