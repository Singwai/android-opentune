package co.opentune.android.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Singwai Chan on 8/9/15.
 */
public class PopularSong {

    public String avatarUrl;
    public String artistName;
    public String songName;

    public PopularSong(String avatarUrl, String artistName, String songName) {
        this.avatarUrl = avatarUrl;
        this.artistName = artistName;
        this.songName = songName;
    }


}
