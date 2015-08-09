package co.opentune.android.Utility;

import android.text.Html;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Singwai on 6/2/15.
 */
public class InputFormatHelper {

    public static final long SECOND = 1000;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;
    public static final long WEEK = DAY * 7;
    public static final long MONTH = DAY * 30;
    public static final long YEAR= MONTH * 12;

    public static String DATE_FORMAT = "MMM-dd, yyyy hh:mm a";

    public static String getDateFormat(long t) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        Date date = new Date(t * 1000);
        return simpleDateFormat.format(date);
    }

    public static String getRelativeTimeString(long unixTimestamp) {
        long now = System.currentTimeMillis();
        long diff = now - (unixTimestamp * 1000);
        if (diff < 0) {
            return "now";
        } else if (diff < HOUR) {
            return diff / MINUTE + "m";

        } else if (diff < DAY) {
            return diff / HOUR + "h";

        } else if (diff < WEEK) {
            return diff / DAY + "d";

        } else if (diff < YEAR) {
            return diff / WEEK + "w";

//        } else if (diff < YEAR) {
//            return diff / MONTH + "M";
        } else {
            return diff / YEAR + "y";
        }
    }


    public static String getRoundedNumber(int number) {
        String result;
        if (number < 0) {
            result = 0 + "";
        } else if (number < 1000) {
            result = number + "";
        } else {
            result = number / 1000 + "k";
        }
        return result;
    }


    public static String prettyURL (String uglyUrl){
        String prettyUrl = uglyUrl.replaceFirst("^(http://)?(www\\.)?", "");
        return prettyUrl;
    }

    public static String esscapeHTMLChar(String query){
        return Html.escapeHtml(query).replaceAll(" " , "%20");
    }
}
