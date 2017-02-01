package homepunk.lesson.first.data;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;

import java.util.List;
import java.util.MissingResourceException;

import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.data.local.SharedPrefencesStorage;
import homepunk.lesson.first.data.network.NetworkStorage;
import homepunk.lesson.first.data.network.TVFetchrAsync;
import homepunk.lesson.first.data.network.TVNetworkParser;
import homepunk.lesson.first.interfaces.Listener;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.utils.NetworkUtils;

public class DataRepository implements Model.TVSeriesModel {
    private final Context context;
    private final DatabaseStorage db;
    private final NetworkStorage rest;
    private final SharedPrefencesStorage sharedPrefences;

    public DataRepository(Context context) {
        this.context = context;
        db = new DatabaseStorage(context);
        rest = new NetworkStorage(context);
        sharedPrefences = new SharedPrefencesStorage(context);
    }

    @Override
    public void fetchTVSeriesList(final Listener presenterListener) {
        if (NetworkUtils.isNetworkAvailable(context)){
            List<TVSeries> tvSeries = db.getAll();
            if (tvSeries != null)
                presenterListener.onResult(tvSeries);
            else presenterListener.onError(new MissingResourceException(null, null, "Database is empty"));
        } else {
            rest.getMostPopularTVSeriesAPI(new TVFetchrAsync.ResultListener() {
                @Override
                public void onResult(@NonNull String result) {
                    try {
                        List<TVSeries> fetchedTv = TVNetworkParser.getTVSeriesFromJson(result);
                        db.saveAll(fetchedTv);
                        presenterListener.onResult(fetchedTv);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        presenterListener.onError(e);
                    }
                }

                @Override
                public void onError(String error) {
                    presenterListener.onError(new NetworkErrorException(error));
                }
            });
        }
    }

    @Override
    public TVSeries fetchTVSeries(Listener resultListener) {
        final TVSeries[] tvSeries = new TVSeries[1];
        rest.getTVSeriesAPI(sharedPrefences.getId(), new TVFetchrAsync.ResultListener() {
            @Override
            public void onResult(@NonNull String result) {
                try {
                    tvSeries[0] = TVNetworkParser.getDetailedByJsonId(result);
                    Log.d(getClass().getCanonicalName(), String.valueOf(tvSeries[0].id));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });
        return tvSeries[0];
    }

    @Override
    public void setSelectedTVSeriesId(int id) {
        sharedPrefences.saveId(id);
    }
}
