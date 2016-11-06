package net.jzapper.android.autoarrival.sim;

import net.jzapper.android.autoarrival.utils.EventUitls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jchionh
 * Date: 11/5/16
 * Time: 8:05 PM
 */

public class SimpleSim
{
    private static double TIMES_PER_MINUTE = 3.0;

    long nextEventMs = 0 + (long) (EventUitls.calcInterArrivalTime(TIMES_PER_MINUTE) * 60 * 1000);
    long countMs = 0;
    long level = 0;

    private ArrayList<String> eventLogs = new ArrayList<>();

    public List<String> resolve(long deltaMs)
    {
        eventLogs.clear();
        long finalMs = countMs + deltaMs;
        eventLogs.add("[Resolve Step] now: " + finalMs / 1000 + "s. next level at: " + nextEventMs / 1000 + "s");
        while (nextEventMs < finalMs) {
            level++;
            nextEventMs = nextEventMs + (long) (EventUitls.calcInterArrivalTime(TIMES_PER_MINUTE) * 60 * 1000);
            eventLogs.add("[Level Up] level: " + level + " next level at: " + nextEventMs / 1000 + "s");
        }
        countMs = finalMs;
        return eventLogs;
    }
}
