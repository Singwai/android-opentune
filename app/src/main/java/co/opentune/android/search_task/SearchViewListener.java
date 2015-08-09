package co.opentune.android.search_task;

import android.util.Log;
import android.widget.SearchView;
import java.util.ArrayList;
import co.opentune.android.entity.PopularSong;


/**
 * Created by Singwai on 6/30/15.
 * <p/>
 * This class is built for android.support.v7.widget.SearchView
 * The main purposes of this class is to handle text change and searchView collapse
 */
public class SearchViewListener implements SearchView.OnQueryTextListener {

    private SearchPopularSongTaskController searchPopularSongTaskController;
    private SearchView searchView;

    public SearchViewListener( SearchView searchView, SearchPopularSongTaskController.SearchResultListener searchResultListener) {
        this.searchView = searchView;
        this.searchPopularSongTaskController = new SearchPopularSongTaskController();
        this.searchPopularSongTaskController.addSearchResultListener(searchResultListener);
    }

    public String getCurrentSearchTerm (){
        if (searchView == null){
            return "";
        }
        return searchView.getQuery().toString();
    }

    public ArrayList<PopularSong> getSearchResult(String query){
        return searchPopularSongTaskController.getSearchResult(query);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("search!" , query + "query");
        searchPopularSongTaskController.search(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("new Text " , newText + "query");
        return true;
    }

}
