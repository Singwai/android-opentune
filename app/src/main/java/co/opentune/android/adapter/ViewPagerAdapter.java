package co.opentune.android.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;


	public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
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

	@Override
	public CharSequence getPageTitle(int position) {
		return position+"";

//		Drawable image = context.getResources().getDrawable(imageResId[position]);
//		image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//		SpannableString sb = new SpannableString(" ");
//		ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//		sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//		return sb;

	}
}
