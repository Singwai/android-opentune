/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package co.opentune.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.opentune.android.FontHelper;
import co.opentune.android.R;
import co.opentune.android.entity.PopularSong;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class PopularSongAdapter extends RecyclerView.Adapter<PopularSongAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<PopularSong> popularSongs;
    private Context context;

    public void clear() {
        if (popularSongs != null) {
            popularSongs.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvArtist;
        private final ImageView iv;
        private final TextView tvSong;

        public ViewHolder(View v, Context context) {
            super(v);
            iv = (ImageView) v.findViewById(R.id.iv);
            tvArtist = (TextView) v.findViewById(R.id.tv_artist);
            FontHelper.changeFont(context, tvArtist);

            tvSong = (TextView) v.findViewById(R.id.tv_song);
            FontHelper.changeFont(context, tvSong);
        }

        public TextView getTvArtist() {
            return tvArtist;
        }

        public ImageView getIv() {
            return iv;
        }

        public TextView getTvSong() {
            return tvSong;
        }


    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    public PopularSongAdapter(ArrayList<PopularSong> popularSongs, Context context) {
        this.context = context;
        this.popularSongs = popularSongs;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.music_card, viewGroup, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(PopularSongAdapter.ViewHolder holder, int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        PopularSong popularSong = this.popularSongs.get(position);

        holder.getTvArtist().setText(popularSong.artistName);
        holder.getTvSong().setText(popularSong.songName);
        ImageView iv = holder.getIv();

        Glide.with(context).load(popularSong.avatarUrl).into(iv);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (popularSongs == null) {
            return 0;
        }
        return popularSongs.size();
    }


    public void appendData(ArrayList<PopularSong> popularSongs) {
        if (this.popularSongs == null) {
            this.popularSongs = popularSongs;
        } else {
            this.popularSongs.addAll(popularSongs);
        }
        notifyDataSetChanged();
    }


}
