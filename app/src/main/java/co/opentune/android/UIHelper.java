package co.opentune.android;

import android.content.Context;


public class UIHelper {

    public static int dpsToPixel(int dps, Context context) {
        //todo move scale to hardwareInfo class
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    public static int pixelToDps(int pixel, Context context) {
        //todo move scale to hardwareInfo class
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pixel / scale - 0.5f);
    }

}
