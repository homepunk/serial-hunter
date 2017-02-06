package homepunk.lesson.series.data.database;

import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.series.model.Series;

public class DbFlowService {
    public static final String LOG_TAG = "DatabaseApi";
    List<Series> seriesList;

    public void saveAll(List<Series> seriesList){
       this.seriesList = new ArrayList<>(seriesList.size());

       for(int i = 0; i < seriesList.size(); i++){
           Series series = new Series();
           series.setId(seriesList.get(i).getId());
           series.setPosterPath(seriesList.get(i).getPosterPath());
           series.save();

           Log.d(LOG_TAG, "saved id " + String.valueOf(seriesList.get(i).getId()));
       }

    }

    public List<Series> getAll(){
        return SQLite.select().from(Series.class).queryList();
    }
}
