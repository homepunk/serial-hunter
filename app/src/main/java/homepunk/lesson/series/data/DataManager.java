package homepunk.lesson.series.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import homepunk.lesson.series.App;
import homepunk.lesson.series.data.database.DbFlowService;
import homepunk.lesson.series.data.rest.TmdbService;
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
    @Inject @Named(WITHOUT_EXPOSE)
    TmdbService detailsService;
    @Inject @Named(WITH_EXPOSE)
    TmdbService onAirService;
    @Inject
    Context context;
    @Inject
    DbFlowService dbService;

    public DataManager(Context context) {
        App.getAppComponent(context).plus(this);
    }

    @Override
    public void fetchOnAirSeries(final RetrofitListener<List<Series>> listener){
        if (!NetworkUtils.isNetworkAvailable(context)){
            List<Series> series = dbService.getAll();
            if (series != null)
                listener.onResult(series);
            else listener.onError(MESSAGE_DATABASE_ERROR);
        } else {
            Call<SeriesResponse> call = onAirService.loadOnAirSeries(1, LANGUAGE_EN, KEY_API);

            call.enqueue(new Callback<SeriesResponse>() {
                @Override
                public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                    List<Series> series = new ArrayList<>(Constants.FILM_COUNT);
                    series.addAll(response.body().getResults());

                    if(series != dbService.getAll())
                        dbService.saveAll(series);

                    listener.onResult(series);
                }

                @Override
                public void onFailure(Call<SeriesResponse> call, Throwable t) {
                    listener.onError(t.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    public void fetchSeriesById(int id, final RetrofitListener<Series> listener) {
        Call<Series> call = detailsService.loadTVSeriesDetails(id, LANGUAGE_EN, KEY_API);

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
