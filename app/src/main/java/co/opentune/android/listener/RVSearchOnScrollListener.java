package co.opentune.android.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Singwai Chan on 8/8/15.
 */
public class RVSearchOnScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Context context = recyclerView.getContext();

    }
}
