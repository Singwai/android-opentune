package co.opentune.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.adapter.ViewPagerAdapter;
import co.opentune.android.fragments.BaseFragment;
import co.opentune.android.fragments.homeFragment.ExploreFragment;

public class MainActivity extends BaseActivity implements OnClickListener {

    private ViewPager pager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);
        pager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        Fragment f = new ExploreFragment();
        fragments.add(f);
        for (int i = 0; i < 3; i++) {
            Bundle data = new Bundle();
            data.putString("text", (i + 1) + "");
            BaseFragment fragment = new BaseFragment();
            //fragment.setArguments(data);
            fragments.add(fragment);
        }
        ViewPagerAdapter fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentPagerAdapter);
        pager.setCurrentItem(0);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onClick(View v) {
        pager.setCurrentItem((Integer) v.getTag());
    }
}
