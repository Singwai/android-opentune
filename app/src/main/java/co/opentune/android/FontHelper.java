package co.opentune.android;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


/**
 * Created by Personal on 6/16/14.
 * Font has to set in Java code.
 * Example to change typeface.git s
 * https://gist.github.com/artem-zinnatullin/7749076
 */
public class FontHelper {

    public static void changeFont(Context context, TextView tv) {
        Typeface montserratRegular = Typeface.createFromAsset(context.getAssets(), context.getResources().getString(R.string.MontserratRegular));
        //Title
        tv.setTypeface(montserratRegular);
    }


}
