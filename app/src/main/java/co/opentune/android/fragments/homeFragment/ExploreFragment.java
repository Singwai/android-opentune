package co.opentune.android.fragments.homeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.adapter.CategoryContentHelper;
import co.opentune.android.entity.Category;


public class ExploreFragment extends Fragment {
    private View rootView;
    private LinearLayout llHsv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_explore, null);
            llHsv = (LinearLayout) rootView.findViewById(R.id.ll_hsv);
            initHorizontalCategory(inflater);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initHorizontalCategory(LayoutInflater inflater) {
        ArrayList<Category> categories = CategoryContentHelper.categories;

        for (int i = 0 ; i  < categories.size() ; i ++){
            View v = inflater.inflate(R.layout.category_cell, null);
            ((TextView) v.findViewById(R.id.tv_title)).setText (categories.get(i).title);
            llHsv.addView(v);
        }

    }

}