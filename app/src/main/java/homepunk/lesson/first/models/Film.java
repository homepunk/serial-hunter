package homepunk.lesson.first.models;


import java.io.Serializable;

public class Film implements Serializable{
    // keys for packing/unpacking intent
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_THUMB_PATH = "key_path";
    private static final String KEY_OVERVIEW = "key_overview";
    private static final String KEY_RELEASE = "key_release";
    private static final String KEY_RATE = "key_rate";
    public long id;
    public String title;
    public String posterPath;
    public String overview;
    public String release;
    public String rate;
    public boolean favorite;
    public long movieId;

    public static final String WIDTH_154 = "w154";
    public static final String WIDTH_342 = "w342";
    public static final String WIDTH_500 = "w500";
    public static final String WIDTH_780 = "w780";

    private static final String URL_IMAGE_TMDB_DEFAULT = "http://image.tmdb.org/t/p/";

    public String getFullPosterPath(String preferedWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(URL_IMAGE_TMDB_DEFAULT);
        sb.append(preferedWidth);
        sb.append(posterPath);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[Movie Item {title=" + title + ", id= " + id + "}]";
    }


}
