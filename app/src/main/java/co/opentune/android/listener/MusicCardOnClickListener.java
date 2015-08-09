package co.opentune.android.listener;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import co.opentune.android.entity.PopularSong;
import co.opentune.android.fragments.dialogFragment.DownloadDialogFragment;


public class MusicCardOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Object o = view.getTag();
        Log.d("Click", view.getContext().getClass().getSimpleName());
        if (o != null && o instanceof PopularSong) {
            Context context = view.getContext();
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            DownloadDialogFragment onBoardingDialogFragment = new DownloadDialogFragment();
            Bundle bundle = new Bundle();

            PopularSong popularSong = (PopularSong) o;
            String s = popularSong.avatarUrl;
            bundle.putString(DownloadDialogFragment.BUNDLE_SONG_IMAGE_URL, s);

            s = popularSong.artistName;
            bundle.putString(DownloadDialogFragment.BUNDLE_SONG_ARTIST, s);

            s = popularSong.songName;
            bundle.putString(DownloadDialogFragment.BUNDLE_SONG_SONG, s);

            s = popularSong.albumName;
            bundle.putString(DownloadDialogFragment.BUNDLE_SONG_ALBUM, s);
            onBoardingDialogFragment.setArguments(bundle);
            onBoardingDialogFragment.show(fragmentManager, "");


        }
    }
}
