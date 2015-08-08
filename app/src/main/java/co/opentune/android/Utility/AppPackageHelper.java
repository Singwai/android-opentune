package co.opentune.android.Utility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Singwai on 7/8/15.
 */
public class AppPackageHelper {

    public static final String PINTEREST_URI = "com.pinterest";

    public static boolean isAppInstalled(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean isAppInstalled;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            isAppInstalled = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            isAppInstalled = false;
        }
        return isAppInstalled;
    }



//    public static String getAppVersion (Context context){
//        try {
//            return getMyPackageInfo(context).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            return "UNKNOWN";
//        }
//    }
//
//    public static String getAppBuild (Context context){
//        try {
//            return getMyPackageInfo(context).versionCode+"";
//        } catch (PackageManager.NameNotFoundException e) {
//            return "UNKNOWN";
//        }
//    }

    public static PackageInfo getMyPackageInfo (Context context) throws PackageManager.NameNotFoundException {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

    }
}
