package homepunk.lesson.series.data.database;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.model.Series;

public class DbFlowService implements Model.SeriesDAO<Series>{
    public static final String LOG_TAG = DbFlowService.class.getCanonicalName();

    @Override
    public void saveAll(List<Series> seriesList){
       for(int i = 0; i < seriesList.size(); i++){
           Series series = new Series();
           series.setId(seriesList.get(i).getId());
           series.setViewType(seriesList.get(i).getViewType());
           series.setTitle(seriesList.get(i).getTitle());
           series.setPosterPath(seriesList.get(i).getPosterPath());
           series.save();
       }
        Log.d(LOG_TAG, "saved " + String.valueOf(seriesList.size()) + "series from network");

    }

    @Override
    public List<Series> getAll(){
        List<Series> series = SQLite.select().from(Series.class).queryList();
        Log.d(LOG_TAG, series.toString());
        return series;
    }

    @Override
    public long save(Series series) throws IOException {
        return 0;
    }

    @Override
    public boolean delete(Series series) {
        return false;
    }

    @Override
    public Series get(int index){
        return SQLite.select().from(Series.class).queryList().get(index);
    }

    @Override
    public boolean clear(){
        SQLite.delete(Series.class).where().query();
        int size = SQLite.select().from(Series.class).queryList().size();
        Log.d(LOG_TAG, "db size after clearing is " + size);
        return size  == 0 ? true : false;
    }

    @Override
    public boolean isAlreadyInDatabase(List<Series> series){
        int equalsNumber = 0;
        List<Series> dbSeries = new ArrayList<>(series.size());
        dbSeries.addAll(getAll());

        if(dbSeries == null || dbSeries.size() == 0)
            return false;

        for (int i = 0; i < series.size(); i++) {
            int id = series.get(i).getId();
            for (int j = 0; j < series.size(); j++) {
                if (id == dbSeries.get(j).getId()) {
                    Log.d(LOG_TAG, "Database series id=" + String.valueOf(get(j).getId()) +
                                   "  Series id=" + series.get(i).getId());
                    equalsNumber++;
                }
            }
            Log.d(LOG_TAG, String.valueOf(series.get(i).getRate()));
        }
        Log.d(LOG_TAG, "DATABASE HAS " + String.valueOf(dbSeries.size()) + " series");
        return equalsNumber!=series.size() ? false : true;
    }
}
