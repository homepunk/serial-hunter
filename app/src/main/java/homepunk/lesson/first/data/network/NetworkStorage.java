package homepunk.lesson.first.data.network;

import android.content.Context;

import homepunk.lesson.first.data.database.Constants;

public class NetworkStorage {
    private final Context context;

    public NetworkStorage(Context context) {
        this.context = context;
    }

    public void getTVSeriesAPI(TVFetchrAsync.ResultListener listener){
        new TVFetchrAsync(listener).execute(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
    }
}
