package homepunk.lesson.first.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.model.TVSeries;

public class TVSeriesDataManager implements Model.SeriesDAO<TVSeries>{
    private static final String LOG_TAG = TVSeriesDataManager.class.getSimpleName();
    private TVSeriesOpenHelper dbHelper;

    public TVSeriesDataManager(Context c) {
        this.dbHelper = new TVSeriesOpenHelper(c);
    }

    @Override
    public long save(TVSeries tvSeries) throws IOException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(TVSeries.KEY_ID, tvSeries.id);
        cv.put(TVSeries.KEY_OVERVIEW, tvSeries.overview);
        cv.put(TVSeries.KEY_POSTER_PATH, tvSeries.posterPath);

        long _id =  db.insert(TVSeries.TABLE_TVSERIES, null, cv);
        db.endTransaction();
        db.close();

        return _id;
    }

    @Override
    public boolean delete(TVSeries tvSeries) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(tvSeries.id)};
        int rows = db.delete(TVSeries.TABLE_TVSERIES, TVSeries.KEY_ID, whereArgs);
        db.close();

        return rows > 0;
    }

    @Override
    public TVSeries get(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(TVSeries.TABLE_TVSERIES,
                TVSeries.projection,
                TVSeries.KEY_ID + " = ? ",
                whereArgs,
                null,
                null,
                null);

        TVSeries item = null;
        while (cursor != null && cursor.moveToFirst())
            item = TVSeries.getItemFromCursor(cursor);

        return item;
    }

    @Override
    public List getAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TVSeries.TABLE_TVSERIES,
                TVSeries.projection,
                null,
                null,
                null,
                null,
                null);
        List<TVSeries> items = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                TVSeries item = TVSeries.getItemFromCursor(cursor);
                items.add(item);
            } while ((cursor.moveToNext()));
        }
        cursor.close();
        return items;
    }

    @Override
    public void saveAll(List<TVSeries> series) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (TVSeries item: series) {
            ContentValues cv = new ContentValues();
//            cv.put(TVSeries.KEY_OVERVIEW, item.overview);
            cv.put(TVSeries.KEY_ID, item.id);
            cv.put(TVSeries.KEY_POSTER_PATH, item.posterPath);
            long id = db.insert(TVSeries.TABLE_TVSERIES, null, cv);
            Log.d(LOG_TAG, "Inserted id=" + id);
        }

        db.close();
    }
}
