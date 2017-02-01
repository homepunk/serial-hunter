package homepunk.lesson.first.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import homepunk.lesson.first.model.TVSeries;

public class TVSeriesOpenHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "tvseries_db";
    public static final int DB_VERSION = 1;

    public TVSeriesOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TVSeries.TABLE_TVSERIES + " (" +
                TVSeries.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                TVSeries.KEY_TITLE + " TEXT NOT NULL," +
//                TVSeries.KEY_OVERVIEW + " TEXT NOT NULL," +
                TVSeries.KEY_POSTER_PATH + " TEXT NOT NULL" +
//                TVSeries.KEY_RATE + " INT NOT NULL" +
                ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE" + TVSeries.TABLE_TVSERIES);
        onCreate(db);
    }
}
