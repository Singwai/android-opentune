package co.opentune.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	private ViewPager pager;
	private int mScreenWidth;
	private int item_width;
	

	private ArrayList<Fragment> fragments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_default);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		item_width = (int) ((mScreenWidth / 4.0 + 0.5f));

		pager = (ViewPager) findViewById(R.id.pager);
		//initNav();
		initViewPager();
	}

	private void initViewPager() {
		fragments = new ArrayList<Fragment>();
    	for (int i = 0; i < 10; i++) {
    		//Bundle data = new Bundle();
    		//data.putString("text", (i+1)+"");
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

	
	
	private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments;

	    public MyFragmentPagerAdapter(FragmentManager fm) {
	        super(fm);
	    }

	    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
	        super(fm);
	        this.fragments = fragments;
	    }

	    @Override
	    public int getCount() {
	        return fragments.size();
	    }

	    @Override
	    public Fragment getItem(int position) {
	        return fragments.get(position);
	    }
	    
		
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
		    Object obj = super.instantiateItem(container, position);
		    return obj;
		}
	}
	
/*	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(final int position) {
			Animation animation = new TranslateAnimation(endPosition, position* item_width, 0, 0);
			
			beginPosition = position * item_width;
			
			currentFragmentIndex = position;
			if (animation != null) {
				animation.setFillAfter(true);
				animation.setDuration(0);
				mImageView.startAnimation(animation);
				mHorizontalScrollView.smoothScrollTo((currentFragmentIndex - 1) * item_width , 0);
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			if(!isEnd){
				if(currentFragmentIndex == position){
					endPosition = item_width * currentFragmentIndex + 
							(int)(item_width * positionOffset);
				}
				if(currentFragmentIndex == position+1){
					endPosition = item_width * currentFragmentIndex - 
							(int)(item_width * (1-positionOffset));
				}
				
				Animation mAnimation = new TranslateAnimation(beginPosition, endPosition, 0, 0);
				mAnimation.setFillAfter(true);
				mAnimation.setDuration(0);
				mImageView.startAnimation(mAnimation);
				mHorizontalScrollView.invalidate();
				beginPosition = endPosition;
			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_DRAGGING) {
				isEnd = false;
			} else if (state == ViewPager.SCROLL_STATE_SETTLING) {
				isEnd = true;
				beginPosition = currentFragmentIndex * item_width;
				if (pager.getCurrentItem() == currentFragmentIndex) {
					// δ������һ��ҳ��
					mImageView.clearAnimation();
					Animation animation = null;
					// �ָ�λ��
					animation = new TranslateAnimation(endPosition, currentFragmentIndex * item_width, 0, 0);
					animation.setFillAfter(true);
					animation.setDuration(1);
					mImageView.startAnimation(animation);
					mHorizontalScrollView.invalidate();
					endPosition = currentFragmentIndex * item_width;
				}
			}
		}

	}*/

	@Override
	public void onClick(View v) {
		pager.setCurrentItem((Integer)v.getTag());
	}
}
