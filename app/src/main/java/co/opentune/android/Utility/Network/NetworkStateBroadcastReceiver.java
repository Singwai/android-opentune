package co.opentune.android.Utility.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Singwai on 7/29/15.
 * This is a receiver to listen for network state change.
 */
public class NetworkStateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkUtility.networkStatusChange(context);
    }
}
