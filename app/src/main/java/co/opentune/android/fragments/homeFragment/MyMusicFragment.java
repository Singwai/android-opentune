	package co.opentune.android.fragments.homeFragment;

    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import co.opentune.android.R;


    public class MyMusicFragment extends Fragment {
        private View rootView;
        private TextView tv_fragment;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            if(rootView==null){
                rootView =inflater.inflate(R.layout.layout_1, null);
                tv_fragment = (TextView) rootView.findViewById(R.id.tv_fragment);
                tv_fragment.setText(System.nanoTime()%10+"");
            }
            ViewGroup parent = (ViewGroup) rootView.getParent();
                if (parent != null) {
                    parent.removeView(rootView);
                }
            return rootView;
        }



}
