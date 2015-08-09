package co.opentune.android.search_task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import co.opentune.android.api.Api;
import co.opentune.android.entity.PopularSong;

import static co.opentune.android.parser.PopularSongParser.SourceType.ITUNE_SEARCH;
import static co.opentune.android.parser.PopularSongParser.parseArray;

/**
 * Created by Singwai on 6/26/15.
 */
public class SearchPopularSongTaskController {
    private ConcurrentHashMap<String, ArrayList<PopularSong>> searchResult;
    private ArrayList<SearchResultListener> registeredListeners;


    public SearchPopularSongTaskController() {
        searchResult = new ConcurrentHashMap<>();
        registeredListeners = new ArrayList<>();
    }

    public ArrayList<PopularSong> getSearchResult(String query) {
        if (searchResult.containsKey(query)) {
            return searchResult.get(query);
        } else {
            return null;
        }
    }

    public void search(String key) {
        if (searchResult.containsKey(key)) {
            Log.d("SearchWorkerThread", key + "searched directly display result");
            notifySearchListener(key);
        } else {
            notifySearchStartListener();
        }
    }

    public void onSearchResultAvailable(String key, ArrayList<PopularSong> popularSongs) {
        searchResult.put(key, popularSongs);
        notifySearchListener(key);
    }


    public void addSearchResultListener(SearchResultListener searchResultListener) {
        registeredListeners.add(searchResultListener);
    }

    private void notifySearchListener(String key) {
        //Usually we have only 1 listener.
        ArrayList<PopularSong> value = searchResult.get(key);
        for (SearchResultListener s : registeredListeners) {
            s.OnSearchResultReturn(key, value);
        }
    }

    private void notifySearchStartListener() {
        for (SearchResultListener s : registeredListeners) {
            s.OnSearchStart();
        }
    }

    private void notifySearchEndListener() {
        for (SearchResultListener s : registeredListeners) {
            s.OnSearchStart();
        }
    }

    public interface SearchResultListener {
        void OnSearchResultReturn(String key, ArrayList<PopularSong> values);

        void OnSearchStart();
    }

    public class ItunesSeach extends AsyncTask<Void, Void, ArrayList<PopularSong>> {
        public String url = "https://itunes.apple.com/search?term=%s&country=us&entity=musicTrack&media=music";
        public String query;

        public ItunesSeach(String query) {
            this.query = query;
            this.url = String.format(url, query);
        }


        @Override
        protected ArrayList<PopularSong> doInBackground(Void... voids) {
            JSONObject result = Api.call(url, null, Api.HTTP_GET);

            if (result != null) {
                JSONArray j = result.optJSONArray("results");
                if (j != null) {
                    return parseArray(j, ITUNE_SEARCH);
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<PopularSong> popularSongs) {
            super.onPostExecute(popularSongs);
            onSearchResultAvailable(query, popularSongs);
        }
    }


}