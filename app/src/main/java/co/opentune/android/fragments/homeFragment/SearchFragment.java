package co.opentune.android.fragments.homeFragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.SpacesItemDecoration;
import co.opentune.android.UIHelper;
import co.opentune.android.adapter.PopularSongAdapter;
import co.opentune.android.api.Api;
import co.opentune.android.entity.PopularSong;
import co.opentune.android.parser.PopularSongParser;


public class SearchFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private PopularSongAdapter popularSongAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search, null);
            initRecyclerView();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }


    private void initRecyclerView() {
        recyclerView = new RecyclerView(this.getActivity());
//        int dpi = UIHelper.dpsToPixel(108, this.getActivity());
//        recyclerView.setPadding(0, dpi, 0, 0);
        int dpi = UIHelper.dpsToPixel(8, this.getActivity());
        recyclerView.addItemDecoration(new SpacesItemDecoration(dpi));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));


        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dpi = UIHelper.dpsToPixel(108, this.getActivity());

        layoutParams.setMargins(0, dpi, 0, 0);
        recyclerView.setLayoutParams(layoutParams);
        popularSongAdapter = new PopularSongAdapter(null, this.getActivity());
        recyclerView.setAdapter(popularSongAdapter);
        ((FrameLayout) rootView).addView(recyclerView);

    }

    public class ItunesSeach extends AsyncTask<Void, Void, ArrayList<PopularSong>> {
        public String url = "https://itunes.apple.com/search?";

        public ItunesSeach(String query) {
            url = url + "term=" + query + "&entity=music";
        }

        @Override
        protected void onPostExecute(ArrayList<PopularSong> popularSongs) {
            super.onPostExecute(popularSongs);
            Log.d("size", popularSongs.size() + "");
            SearchFragment.this.popularSongAdapter.appendData(popularSongs);
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
                            ArrayList<PopularSong> popularSongs = PopularSongParser.ItunePopularParser.parseArray(raw);
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
