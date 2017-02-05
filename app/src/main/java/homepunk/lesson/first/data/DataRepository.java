package homepunk.lesson.first.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.first.App;
import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.data.rest.TmdbClient;
import homepunk.lesson.first.data.rest.TmdbApi;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.model.Series;
import homepunk.lesson.first.model.SeriesResponse;
import homepunk.lesson.first.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static homepunk.lesson.first.data.database.Constants.DATABASE_ERROR;
import static homepunk.lesson.first.data.database.Constants.KEY_API;
import static homepunk.lesson.first.data.database.Constants.LANGUAGE_EN;
import static homepunk.lesson.first.interfaces.Listeners.RetrofitListListener;
import static homepunk.lesson.first.interfaces.Listeners.RetrofitListener;

public class DataRepository implements Model.DataRepositoryModel {
    @Inject DatabaseStorage db;
    private final Context context;

    public DataRepository(Context context) {
        App.getAppComponent(context).plus(this);
        this.context = context;
    }

    @Override
    public void fetchOnAirSeries(final RetrofitListListener listener){
        if (!NetworkUtils.isNetworkAvailable(context)){
            List<Series> series = db.getAll();
            if (series != null)
                listener.onResult(series);
            else listener.onError(DATABASE_ERROR);
        } else {
            TmdbApi service = TmdbClient.getClientWithExposes();
            Call<SeriesResponse> call = service.loadOnAirSeries(1, LANGUAGE_EN, KEY_API);

            call.enqueue(new Callback<SeriesResponse>() {
                @Override
                public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                    List<Series> series = response.body().getResults();
                    db.saveAll(series);
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
    public void fetchSeriesById(int id, final RetrofitListener listener) {
        TmdbApi service = TmdbClient.getClientWithoutExposes();
        Call<Series> call = service.loadTVSeriesDetails(id, LANGUAGE_EN, KEY_API);

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
