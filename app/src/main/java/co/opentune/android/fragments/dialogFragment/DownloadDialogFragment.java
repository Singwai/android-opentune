package co.opentune.android.fragments.dialogFragment;

/**
 * Created by Singwai on 4/29/15.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import co.opentune.android.FontHelper;
import co.opentune.android.R;
import co.opentune.android.entity.PopularSong;


public class DownloadDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = this.getDialog();

        if (dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
   }

    public static final String BUNDLE_SONG_SONG = "SONG_NAME";
    public static final String BUNDLE_SONG_ARTIST = "ARTIST_NAME";
    public static final String BUNDLE_SONG_ALBUM = "ALBUM_NAME";
    public static final String BUNDLE_SONG_IMAGE_URL = "IMAGE_URL";

    private PopularSong popularSong;

    public DownloadDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.on_boarding, container);
        Dialog dialog = this.getDialog();
        //Hide title bar
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Context context = dialog.getContext();


        ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
        Glide.with(context).load(popularSong.avatarUrl).into(iv);

        TextView tv = (TextView) view.findViewById(R.id.tv_song);
        tv.setText(popularSong.songName);
        FontHelper.changeFont(context, tv);

        tv = (TextView) view.findViewById(R.id.tv_artist);
        tv.setText(popularSong.artistName);
        FontHelper.changeFont(context, tv);

        tv = (TextView) view.findViewById(R.id.tv_album);
        tv.setText(popularSong.albumName);
        FontHelper.changeFont(context, tv);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPopularSong();
    }

    private void initPopularSong() {
        Bundle b = this.getArguments();
        String song = b.getString(BUNDLE_SONG_SONG);
        String artist = b.getString(BUNDLE_SONG_ARTIST);
        String album = b.getString(BUNDLE_SONG_ALBUM);
        String imageUrl = b.getString(BUNDLE_SONG_IMAGE_URL);

        popularSong = new PopularSong(imageUrl, artist, song, album);


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

    }
}
