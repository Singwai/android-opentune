package co.opentune.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.ArrayList;

import co.opentune.android.adapter.MyFragmentPagerAdapter;

public class MainActivity extends BaseActivity implements OnClickListener {

	private ViewPager pager;
	private ArrayList<Fragment> fragments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		pager = (ViewPager) findViewById(R.id.pager);
		initViewPager();
	}

	private void initViewPager() {
		fragments = new ArrayList<>();
    	for (int i = 0; i < 10; i++) {
    		Bundle data = new Bundle();
    		data.putString("text", (i+1)+"");
    		BaseFragment fragment = new BaseFragment();
    		//fragment.setArguments(data);
    		fragments.add(fragment);
		}
    	MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
		pager.setAdapter(fragmentPagerAdapter);
		//pager.setOnPageChangeListener(new MyOnPageChangeListener());
		pager.setCurrentItem(0);
	}
/*

	private void initNav() {
		for (int i = 0 ; i < 100; i++) {
			RelativeLayout layout = new RelativeLayout(this);
			TextView view = new TextView(this);
			view.setText("��"+(i+1)+"��");
			RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			layout.addView(view, params);
			mLinearLayout.addView(layout, (int)(mScreenWidth/4 + 0.5f), 50);
			layout.setOnClickListener(this);
			layout.setTag(i);
		}
	}
*/


	@Override
	public void onClick(View v) {
		pager.setCurrentItem((Integer)v.getTag());
	}
}
