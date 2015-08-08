package co.opentune.android.singleton;

import android.app.Application;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import co.opentune.android.api.Api;


public class OpenTuneApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Api.init(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();
    }

}