package homepunk.lesson.first.data;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONException;

import java.util.List;
import java.util.MissingResourceException;

import homepunk.lesson.first.data.database.DatabaseStorage;
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

    public DataRepository(Context context) {
        this.context = context;
        db = new DatabaseStorage(context);
        rest = new NetworkStorage(context);
    }

    @Override
    public void fetchTVSeries(final Listener presenterListener) {
        if (NetworkUtils.isNetworkAvailable(context)){
            List<TVSeries> tvSeries = db.getAll();
            if (tvSeries != null)
                presenterListener.onResult(tvSeries);
            else presenterListener.onError(new MissingResourceException(null, null, "Database is empty"));
        } else {
            rest.getTVSeriesAPI(new TVFetchrAsync.ResultListener() {
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
}
