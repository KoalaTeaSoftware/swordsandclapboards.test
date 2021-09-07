package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
public class Dates {
    /**
     * The following small set is a simple set that result in a string that could be directly used to enter a date relative to now
     * Get the time now as a string that has been formatted as you indicate
     *
     * @param df - format of the output see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * @return - now, as a string, in the format that you require
     */
    public static String now(String df) {
        DateFormat dateFormat = new SimpleDateFormat(df);
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * @param df   - format of the output see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * @param diff - number of days to add, or remove from the now
     * @return - result, as a string, in the format that you require
     */
    public static String nowPlusDays(String df, int diff) {
        DateFormat dateFormat = new SimpleDateFormat(df);
        Date date = new Date();
        date = addDays(date, diff);
        return dateFormat.format(date);
    }

    public static String nowPlusHours(String df, int diff) {
        DateFormat dateFormat = new SimpleDateFormat(df);
        Date date = new Date();
        date = addHours(date, diff);
        return dateFormat.format(date);
    }

    public static String nowPlusMinutes(String df, int diff) {
        DateFormat dateFormat = new SimpleDateFormat(df);
        Date date = new Date();
        date = addMinutes(date, diff);
        return dateFormat.format(date);
    }

    public static String nowPlusSeconds(String df, int diff) {
        DateFormat dateFormat = new SimpleDateFormat(df);
        Date date = new Date();
        date = addSeconds(date, diff);
        return dateFormat.format(date);
    }


    /**
     * This is a series of similar methods that operate on Date objects.
     * So you should be able to easily adjust by days, hours, and so on, by just stacking up the calls
     *
     * @param date - Starting point
     * @param days - number of days to add to that starting point
     * @return - the resulting date
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours); //minus number would decrement the hours
        return cal.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the minutes
        return cal.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds); //minus number would decrement the seconds
        return cal.getTime();
    }
}
