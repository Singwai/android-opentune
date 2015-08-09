package co.opentune.android.fragments.homeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import co.opentune.android.R;


public class ExploreFragment extends Fragment {
    private View rootView;
    private HorizontalScrollView hsvCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_explore, null);
            hsvCategory = (HorizontalScrollView) rootView.findViewById(R.id.hsv_category);
            initHorizontalCategory();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initHorizontalCategory() {
    }

}
