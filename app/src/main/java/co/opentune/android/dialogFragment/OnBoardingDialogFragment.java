//package co.opentune.android.dialogFragment;
//
///**
// * Created by Singwai on 4/29/15.
// */
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.widget.Button;
//
//import se.yo.android.bloglovincore.R;
//import se.yo.android.bloglovincore.activity.BaseActivity;
//import se.yo.android.bloglovincore.activity.ExploreActivity;
//
//
//public class OnBoardingDialogFragment extends BaseDialogFragment implements View.OnClickListener, DialogInterface.OnKeyListener {
//
//    private Button btnOnBoarding;
//
//    public OnBoardingDialogFragment() {
//        // Empty constructor required for DialogFragment
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.on_boarding, container);
//
//        Dialog dialog = this.getDialog();
//        //Hide title bar
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setOnKeyListener(this);
//
//
////        ImageButton imgBtn = ((ImageButton) view.findViewById(R.id.btn_register_close));
////        imgBtn.setOnClickListener(this);
//
//        btnOnBoarding = (Button) view.findViewById(R.id.btn_on_boarding);
//        btnOnBoarding.setOnClickListener(this);
//        //Set Context
//        context = this.getActivity();
//
//        return view;
//    }
//
//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
////        if (i == R.id.btn_register_close || i == R.id.wrapper_register_class) {
////            this.dismiss();
////        } else
//        if (i == R.id.btn_on_boarding) {
//            ((BaseActivity) this.getActivity()).activityRouter(BaseActivity.ACTIVITY_ACTION_EXPLORE, ExploreActivity.FRAGMENT_ACTION_BLOG);
//            this.dismiss();
//        }
//
//
//    }
//
//    @Override
//    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//        return (keyCode == android.view.KeyEvent.KEYCODE_BACK); // if back button press, consume it else, pass it down.
//    }
//}
