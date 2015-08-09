package co.opentune.android.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.opentune.android.R;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments;
	private Context context;

	public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, Context context) {
		super(fm);
		this.fragments = fragments;
		this.context = context;
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

//		Drawable image = ContextCompat.getDrawable(context , R.drawable.ic_launcher);
//		image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//		SpannableString sb = new SpannableString(" ");
//		ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//		sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return position+"";

	}
}
