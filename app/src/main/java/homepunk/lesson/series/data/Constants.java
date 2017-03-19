package homepunk.lesson.series.data;

public class Constants {
    public static final int FILM_COUNT = 20;

    public static final String KEY_API = "515e5bce899580aada662ed279a9a94b";
    public static final String KEY_ID = "KEY_ID";

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String LANGUAGE_RU = "ru-RU";
    public static final String LANGUAGE_EN = "en-US";
    public static final int PAGE_ONE = 1;

    public static final String SHARED_PREF_NAME = "SharedPref";
    public static final String SHARED_PREF_KEY_ID = "id";

    public static final String[] data = {"По рейтингу", "По популярности", "Все сериалы"};

    public static final String MESSAGE_DATABASE_ERROR = "Database read error";

    public static final String WITH_EXPOSE = "with expose";
    public static final String WITHOUT_EXPOSE = "without expose";

    public static final String[] CURSOR_SEARCH_COLUMNS = new String[]{"_id", "SERIES_POSTER", "SERIES_TITLE"};
}
