package net.jzapper.android.autoarrival.utils;

/**
 * Created by jchionh
 * Date: 11/4/16
 * Time: 10:53 PM
 */

public class EventUitls
{
    /**
     *
     * * return inter-arrivial timing
     *
     * poisson inter-arrival process
     * t = t + (-1/rate)Ln(U), where U is the uniform dist between 0 and 1.
     * rate = x number of occurrences per time unit.
     * e.g. 3 decisions per minute, 0.05 decision per second, etc.
     *
     * @param rate  the desired rate of the event per unit time
     * @return      the next time that the event will happen
     */
    public static double calcInterArrivalTime(double rate) {
        return ((-1.0 / rate) * Math.log(Math.random()));
    }
}
