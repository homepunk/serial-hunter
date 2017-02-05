package homepunk.lesson.first.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.first.App;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.model.Series;

public class DatabaseStorage implements Model.SeriesDAO<Series>{
    @Inject
    SeriesOpenHelper dbHelper;
    private static final String LOG_TAG = DatabaseStorage.class.getSimpleName();

    public DatabaseStorage(Context context) {
        App.getAppComponent(context).plus(this);
    }

    @Override
    public long save(Series tvSeries) throws IOException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(Series.KEY_ID, tvSeries.getId());
        cv.put(Series.KEY_OVERVIEW, tvSeries.getOverview());
        cv.put(Series.KEY_POSTER_PATH, tvSeries.getPosterPath());

        long _id =  db.insert(Series.TABLE_TVSERIES, null, cv);
        db.endTransaction();
        db.close();

        return _id;
    }

    @Override
    public boolean delete(Series tvSeries) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(tvSeries.getId())};
        int rows = db.delete(Series.TABLE_TVSERIES, Series.KEY_ID, whereArgs);
        db.close();

        return rows > 0;
    }

    @Override
    public Series get(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(Series.TABLE_TVSERIES,
                Series.projection,
                Series.KEY_ID + " = ? ",
                whereArgs,
                null,
                null,
                null);

        Series item = null;
        while (cursor != null && cursor.moveToFirst())
            item = Series.getItemFromCursor(cursor);

        return item;
    }

    @Override
    public List getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Series.TABLE_TVSERIES,
                Series.projection,
                null,
                null,
                null,
                null,
                null);
        List<Series> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Series item = Series.getItemFromCursor(cursor);
                items.add(item);
            } while ((cursor.moveToNext()));
        }
        cursor.close();
        return items;
    }

    @Override
    public void saveAll(List<Series> series) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (Series item: series) {
            ContentValues cv = new ContentValues();
//            cv.put(TVSeries.KEY_OVERVIEW, item.overview);
            cv.put(Series.KEY_ID, item.getId());
            cv.put(Series.KEY_POSTER_PATH, item.getPosterPath());
            long id = db.insert(Series.TABLE_TVSERIES, null, cv);
            Log.d(LOG_TAG, "Inserted id=" + id);
        }

        db.close();
    }
}
