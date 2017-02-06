package homepunk.lesson.series.model;

import com.raizlabs.android.dbflow.annotation.Database;

import static homepunk.lesson.series.model.SeriesDatabase.DATABASE_NAME;
import static homepunk.lesson.series.model.SeriesDatabase.VERSION;

@Database(name = DATABASE_NAME, version = VERSION)
public class SeriesDatabase {
    public static final String DATABASE_NAME = "seriesdb";

    public static final int VERSION = 1;
}
