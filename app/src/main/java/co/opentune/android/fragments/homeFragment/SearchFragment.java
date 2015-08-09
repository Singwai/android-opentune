package co.opentune.android.fragments.homeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.SearchView;

import java.util.ArrayList;

import co.opentune.android.R;
import co.opentune.android.SpacesItemDecoration;
import co.opentune.android.UIHelper;
import co.opentune.android.adapter.PopularSongAdapter;
import co.opentune.android.entity.PopularSong;
import co.opentune.android.search_task.SearchPopularSongTaskController.SearchResultListener;
import co.opentune.android.search_task.SearchViewListener;


public class SearchFragment extends Fragment implements SearchResultListener {
    private View rootView;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private PopularSongAdapter popularSongAdapter;
    private SearchViewListener searchViewListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_search, null);
            searchView = (SearchView) rootView.findViewById(R.id.sv);
            initRecyclerView();
            searchViewListener = new SearchViewListener(searchView, this);
            searchView.setOnQueryTextListener(searchViewListener);
            recyclerView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent motionEvent) {
                    View view = SearchFragment.this.getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) SearchFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    return false;
                }
            });
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
        dpi = UIHelper.dpsToPixel(56, this.getActivity());

        layoutParams.setMargins(0, dpi, 0, 0);
        recyclerView.setLayoutParams(layoutParams);
        popularSongAdapter = new PopularSongAdapter(null, this.getActivity());
        recyclerView.setAdapter(popularSongAdapter);
        ((FrameLayout) rootView).addView(recyclerView);

    }

    @Override
    public void OnSearchResultReturn(String key, ArrayList<PopularSong> values) {
        popularSongAdapter.clear();
        popularSongAdapter.appendData(values);

        View view = SearchFragment.this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) SearchFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    public void OnSearchStart() {

    }

}
