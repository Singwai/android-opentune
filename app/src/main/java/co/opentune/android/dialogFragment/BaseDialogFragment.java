package co.opentune.android.dialogFragment;

import android.content.Context;
import android.support.v4.app.DialogFragment;


public class BaseDialogFragment extends DialogFragment  {
    protected Context context;

    public BaseDialogFragment(Context context){
        this.context = context;
    }
}
