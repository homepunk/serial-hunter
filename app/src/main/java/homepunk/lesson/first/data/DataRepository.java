package homepunk.lesson.first.data;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONException;

import java.util.List;
import java.util.MissingResourceException;

import javax.inject.Inject;

import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.data.local.SharedPrefencesStorage;
import homepunk.lesson.first.data.network.NetworkStorage;
import homepunk.lesson.first.data.network.TVFetchrAsync;
import homepunk.lesson.first.data.network.TVNetworkParser;
import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.utils.NetworkUtils;

public class DataRepository implements Model.TVSeriesModel {
    @Inject DatabaseStorage db;
    @Inject NetworkStorage rest;
    @Inject SharedPrefencesStorage sharedPrefences;

    private final Context context;

    public DataRepository(Context context) {
        this.context = context;
    }

    @Override
    public void fetchSeriesList(final Listeners.ListListener presenterListener) {
        if (NetworkUtils.isNetworkAvailable(context)){
            List<TVSeries> tvSeries = db.getAll();
            if (tvSeries != null)
                presenterListener.onResult(tvSeries);
            else presenterListener.onError(new MissingResourceException(null, null, "Database is empty"));
        } else {
            rest.getMostPopularSeriesAPI(new TVFetchrAsync.ResultListener() {
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
    public void fetchSeriesById(int id, final Listeners.Listener resultListener) {
        rest.getSeriesByIdAPI(id, new TVFetchrAsync.ResultListener() {
            @Override
            public void onResult(@NonNull String result) {
                try {
                    TVSeries tvSeries = TVNetworkParser.getDetailedByJsonId(result);
                    resultListener.onResult(tvSeries);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                resultListener.onError(new NetworkErrorException(error));
            }
        });

    }
}
