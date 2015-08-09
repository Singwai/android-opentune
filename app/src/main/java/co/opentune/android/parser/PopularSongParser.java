package co.opentune.android.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.opentune.android.entity.PopularSong;

/**
 * Created by Singwai Chan on 8/9/15.
 */
public class PopularSongParser {

    public enum SourceType {
        ITUNE_POPULAR,
        ITUNE_SEARCH;
    }


    public static ArrayList<PopularSong> parseArray(JSONArray jsonArray, SourceType sourceType) {
        switch (sourceType) {
            case ITUNE_POPULAR:
                try {
                    return ItunePopularParser.parseArray(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case ITUNE_SEARCH:
                try {
                    return ItuneSearchParser.parseArray(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
    }


    private static class ItuneSearchParser {
        public static ArrayList<PopularSong> parseArray(JSONArray raw) throws JSONException {
            ArrayList<PopularSong> result = new ArrayList<>();
            for (int i = 0; i < raw.length(); i++) {
                PopularSong p = parseJSONObject(raw.optJSONObject(i));
                if (p != null) {
                    result.add(p);
                }
            }
            return result;
        }

        private static PopularSong parseJSONObject(JSONObject jsonObject) {
            if (jsonObject != null) {
                String artistName = parseArtistName(jsonObject);
                String songName = parseSongName(jsonObject);
                String url = parseAvatarUrl(jsonObject);
                if (artistName != null && songName != null && url != null) {
                    return new PopularSong(url, artistName, songName);
                }
            }
            return null;
        }

        private static String parseAvatarUrl(JSONObject jsonObject) {
            if (jsonObject != null) {
                return jsonObject.optString("artworkUrl100");
            }
            return null;
        }

        private static String parseArtistName(JSONObject jsonObject) {
            if (jsonObject != null) {
                return jsonObject.optString("artistName");
            }
            return null;

        }

        private static String parseSongName(JSONObject jsonObject) {
            if (jsonObject != null) {
                return j.optString("trackName");
            }
            return null;

        }
    }

    private static class ItunePopularParser {

        public static ArrayList<PopularSong> parseArray(JSONArray raw) throws JSONException {
            ArrayList<PopularSong> result = new ArrayList<>();
            for (int i = 0; i < raw.length(); i++) {
                PopularSong p = parseJSONObject(raw.optJSONObject(i));
                if (p != null) {
                    result.add(p);
                }
            }
            return result;
        }

        private static PopularSong parseJSONObject(JSONObject jsonObject) {
            if (jsonObject != null) {
                String artistName = parseArtistName(jsonObject);
                String songName = parseSongName(jsonObject);
                String url = parseAvatarUrl(jsonObject);

                if (artistName != null && songName != null && url != null) {
                    return new PopularSong(url, artistName, songName);
                }
            }
            return null;
        }

        private static String parseAvatarUrl(JSONObject jsonObject) {
            if (jsonObject != null) {
                JSONArray j = jsonObject.optJSONArray("im:image");
                if (j != null && j.length() >= 1) {
                    JSONObject j2 = j.optJSONObject(2);
                    if (j2 != null) {
                        return j2.optString("label");
                    }
                }
            }
            return null;
        }

        private static String parseArtistName(JSONObject jsonObject) {
            if (jsonObject != null) {
                JSONObject j = jsonObject.optJSONObject("im:artist");
                if (j != null) {
                    return j.optString("label");
                }
            }
            return null;

        }

        private static String parseSongName(JSONObject jsonObject) {
            if (jsonObject != null) {
                JSONObject j = jsonObject.optJSONObject("im:name");
                if (j != null) {
                    return j.optString("label");
                }
            }
            return null;
        }
    }

}
