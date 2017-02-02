package homepunk.lesson.first.data.network;

import android.content.Context;

import homepunk.lesson.first.data.database.Constants;

public class NetworkStorage {
    private final Context context;

    public NetworkStorage(Context context) {
        this.context = context;
    }

    public void getMostPopularSeriesAPI(TVFetchrAsync.ResultListener listener){
        new TVFetchrAsync(listener).execute(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
    }

    public void getSeriesByIdAPI(int id, TVFetchrAsync.ResultListener listener){
        new TVFetchrAsync(listener).execute(Constants.TV_REFENECE + id + Constants.LANGUAGE_RU + Constants.API_KEY);
    }
}
