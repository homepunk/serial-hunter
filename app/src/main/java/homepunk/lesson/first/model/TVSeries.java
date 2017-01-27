package homepunk.lesson.first.model;


import android.database.Cursor;

import java.io.Serializable;

public class TVSeries implements Serializable {
    public static final String WIDTH_154 = "w154";
    public static final String WIDTH_342 = "w342";
    public static final String WIDTH_500 = "w500";
    public static final String WIDTH_780 = "w780";

    private static final String URL_IMAGE_TMDB_DEFAULT = "http://image.tmdb.org/t/p/";

    public static final String KEY_TITLE = "original_title";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_RATE = "vote_average";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_ID = "id";
    public static final String TABLE_TVSERIES = "tvseries";

    public static String[] projection = {
            KEY_ID,
            KEY_POSTER_PATH
    };

    public int id;
    public String title;
    public String titleOriginal;
    public String posterPath;
    public String overview;
    public String release;
    public String rate;
    public boolean favorite;

    public static TVSeries getItemFromCursor(Cursor c) {
        TVSeries item = new TVSeries();
        item.id = c.getInt(c.getColumnIndex(TVSeries.KEY_ID));
//        item.title = c.getString(c.getColumnIndex(TVSeries.KEY_TITLE));
//        item.overview = c.getString(c.getColumnIndex(TVSeries.KEY_OVERVIEW));
        item.posterPath = c.getString(c.getColumnIndex(TVSeries.KEY_POSTER_PATH));
//        item.rate = c.getString(c.getColumnIndex(TVSeries.KEY_RATE));

        return item;
    }

    public String getFullPosterPath(String preferedWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL_IMAGE_TMDB_DEFAULT);
        sb.append(preferedWidth);
        sb.append(posterPath);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[TVSeries Item {title=" + title + ", id= " + id + "}]";
    }

}
