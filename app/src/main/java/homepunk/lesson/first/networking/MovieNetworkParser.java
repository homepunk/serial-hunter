package homepunk.lesson.first.networking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.db.Constants;
import homepunk.lesson.first.models.Film;

public class MovieNetworkParser {
    private static final String KEY_RESULTS = "results";
    private static final String KEY_TITLE = "original_title";
    private static final String KEY_POSTER_PATH = "poster_path";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_RATE = "vote_average";
    private static final String KEY_RELEASE_DATE = "release_date";

    public static List<Film> getFilmsFromJson(String json) throws JSONException {
        JSONObject root = new JSONObject(json);
        JSONArray jsonFilmsArray = root.getJSONArray(KEY_RESULTS);

        List<Film> filmsList = new ArrayList<>(Constants.FILM_COUNT);

        int length = root.length() < Constants.FILM_COUNT ? jsonFilmsArray.length() : Constants.FILM_COUNT;
        for (int i = 0; i < length; i++) {
            Film film = new Film();
            JSONObject jsonFilm = jsonFilmsArray.getJSONObject(i);
            film.title = jsonFilm.getString(KEY_TITLE);
            film.posterPath = jsonFilm.getString(KEY_POSTER_PATH);
            film.overview = jsonFilm.getString(KEY_OVERVIEW);

            filmsList.add(film);
        }
        return filmsList;
    }
}
