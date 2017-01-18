package homepunk.lesson.first.model;


import java.io.Serializable;

public class TVSeries implements Serializable {
    public static final String WIDTH_154 = "w154";
    public static final String WIDTH_342 = "w342";
    public static final String WIDTH_500 = "w500";
    public static final String WIDTH_780 = "w780";

    private static final String URL_IMAGE_TMDB_DEFAULT = "http://image.tmdb.org/t/p/";

    public int id;
    public String title;
    public String titleOriginal;
    public String posterPath;
    public String overview;
    public String release;
    public String rate;
    public boolean favorite;


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
