package net.jzapper.android.autoarrival.event_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.jzapper.android.autoarrival.R;

import java.util.LinkedList;

/**
 * Created by jchionh
 * Date: 11/4/16
 * Time: 11:27 PM
 */

public class EventAdapter extends ArrayAdapter<String>
{
    public EventAdapter(@NonNull Context context)
    {
        super(context, R.layout.event_item, R.id.event_text, new LinkedList<String>());
    }

    /**
     * Add the log to the top of the list
     * @param eventLog
     */
    public void addFirst(@NonNull String eventLog)
    {
        insert(eventLog, 0);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent)
    {
        // cache our incoming view to re-use
        View resultView = convertView;

        // if we don't have a view, let's create it
        if (resultView == null)
        {
            Context context = getContext();
            LayoutInflater layoutInflater =

                    (LayoutInflater) context.getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
            resultView = layoutInflater.inflate(R.layout.event_item, null);
        }

        String eventLog = getItem(position);
        String taggedItem = (String) resultView.getTag();

        if (taggedItem == null || !taggedItem.equals(eventLog)) {
            // and assign values to the view
            TextView textView = (TextView) resultView.findViewById(R.id.event_text);
            textView.setText(eventLog);

            // tag our item into the resultview
            resultView.setTag(eventLog);
        }
        return resultView;
    }
}
