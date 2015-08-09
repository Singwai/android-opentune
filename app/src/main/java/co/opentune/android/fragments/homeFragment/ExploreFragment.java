package co.opentune.android.fragments.homeFragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.adapter.CategoryContentHelper;
import co.opentune.android.adapter.PopularSongAdapter;
import co.opentune.android.api.Api;
import co.opentune.android.entity.Category;
import co.opentune.android.entity.PopularSong;


public class ExploreFragment extends Fragment {
    private View rootView;
    private LinearLayout llHsv;
    private RecyclerView recyclerView;
    private PopularSongAdapter popularSongAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_explore, null);
//            llHsv = (LinearLayout) rootView.findViewById(R.id.ll_hsv);
//            initHorizontalCategory(inflater);
            initRecyclerView();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        new ItunesPopularTask("").execute();
        return rootView;
    }

    private void initRecyclerView() {
        recyclerView = new RecyclerView(this.getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));
        popularSongAdapter = new PopularSongAdapter(null, this.getActivity());
        ((FrameLayout) rootView).addView(recyclerView);

    }

    private void initHorizontalCategory(LayoutInflater inflater) {
        ArrayList<Category> categories = CategoryContentHelper.categories;
//        int dpi = UIHelper.dpsToPixel(64, this.getActivity());
        Activity activity = this.getActivity();
//        for (int i = 0 ; i  < categories.size() ; i ++){
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpi, dpi);
        View v = inflater.inflate(R.layout.category_cell, null);
//            v.setLayoutParams(layoutParams);
        ((TextView) v.findViewById(R.id.tv_title)).setText(categories.get(1).title);
        ((ImageView) v.findViewById(R.id.iv_icon)).setImageDrawable(ContextCompat.getDrawable(activity, categories.get(1).drawableIcon));
//            llHsv.addView(v);
//        }
        ((FrameLayout) rootView).addView(v);

    }

    public class ItunesPopularTask extends AsyncTask<Void, Void, ArrayList<PopularSong>> {
        public final String url = "https://itunes.apple.com/us/rss/topsongs/limit=10/genre=6/json";

        public ItunesPopularTask(String categoryId) {


        }

        @Override
        protected void onPostExecute(ArrayList<PopularSong> popularSongs) {
            super.onPostExecute(popularSongs);
            Log.d("size" , popularSongs.size()+"");
            ExploreFragment.this.popularSongAdapter.appendData(popularSongs);
        }

        @Override
        protected ArrayList<PopularSong> doInBackground(Void... voids) {
            JSONObject result = Api.call(url, null, Api.HTTP_GET);

            if (result != null) {
                result = result.optJSONObject("feed");
                if (result != null) {
                    JSONArray raw = result.optJSONArray("entry");
                    if (raw != null) {
                        try {
                            ArrayList<PopularSong> popularSongs = PopularSong.parseArray(raw);
                            return popularSongs;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return null;

        }

    }
}
