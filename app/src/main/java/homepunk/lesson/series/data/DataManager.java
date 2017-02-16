package homepunk.lesson.series.data;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import homepunk.lesson.series.App;
import homepunk.lesson.series.data.database.DbFlowService;
import homepunk.lesson.series.data.rest.RetrofitService;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.model.SeriesResponse;
import homepunk.lesson.series.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static homepunk.lesson.series.data.Constants.KEY_API;
import static homepunk.lesson.series.data.Constants.LANGUAGE_EN;
import static homepunk.lesson.series.data.Constants.MESSAGE_DATABASE_ERROR;
import static homepunk.lesson.series.data.Constants.WITHOUT_EXPOSE;
import static homepunk.lesson.series.data.Constants.WITH_EXPOSE;
import static homepunk.lesson.series.interfaces.Listeners.RetrofitListener;
import static homepunk.lesson.series.interfaces.Model.DataManagerModel;

public class DataManager implements DataManagerModel {
    @Inject
    @Named(WITHOUT_EXPOSE)
    RetrofitService seriesService;
    @Inject
    @Named(WITH_EXPOSE)
    RetrofitService listService;
    @Inject
    Context context;
    @Inject
    DbFlowService dbService;

    public DataManager(Context context) {
        App.getAppComponent(context).plus(this);
    }

    @Override
    public void fetchOnAirSeries(final RetrofitListener<List<Series>> listener) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            List<Series> series = dbService.getAll();
            if (series != null)
                listener.onResult(series);
            else listener.onError(MESSAGE_DATABASE_ERROR);
        } else {
            Call<SeriesResponse> call = listService.loadOnAirSeries(1, LANGUAGE_EN, KEY_API);

            call.enqueue(new Callback<SeriesResponse>() {
                @Override
                public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                    List<Series> series = response.body().setViewType(Series.GRID_TYPE);
//                    Collections.sort(series);
                    if(!dbService.getAll().isEmpty()) {
                        if (!dbService.isAlreadyInDatabase(series)) {
//                            TODO: TIMBER LIBRARY
                            Log.d("DataManager", "List saved succesfully");
                            dbService.clear();
                            dbService.saveAll(series);
                        } else {
                            Log.d("DataManager", "The same list is already exists in database");
                        }
                    } else dbService.saveAll(series);
                listener.onResult(series);
            }

            @Override
            public void onFailure (Call < SeriesResponse > call, Throwable t){
                listener.onError(t.getLocalizedMessage());
            }
        });
    }
}

    @Override
    public void fetchTopRatedSeries(final RetrofitListener<List<Series>> listener) {
        Call<SeriesResponse> call = listService.loadTopRatedSeries(1, LANGUAGE_EN, KEY_API);
        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                listener.onResult(response.body().setViewType(Series.BACKDROP_TYPE));
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void fetchSeriesById(int id, final RetrofitListener<Series> listener) {
        Call<Series> call = seriesService.loadTVSeriesDetails(id, LANGUAGE_EN, KEY_API);

        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                listener.onResult(response.body());
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                listener.onError(t.getLocalizedMessage());
            }
        });
    }
}
