	package co.opentune.android.fragments.homeFragment;

    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import co.opentune.android.R;
    import co.opentune.android.adapter.PopularSongAdapter;


    public class MyMusicFragment extends Fragment {
        private View rootView;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            if(rootView==null){
                rootView =inflater.inflate(R.layout.layout_1, null);
            }
            ViewGroup parent = (ViewGroup) rootView.getParent();
                if (parent != null) {
                    parent.removeView(rootView);
                }
            return rootView;
        }





}
