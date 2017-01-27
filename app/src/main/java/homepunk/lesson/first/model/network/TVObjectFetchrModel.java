package homepunk.lesson.first.model.network;

import android.text.TextUtils;

import org.json.JSONException;

import homepunk.lesson.first.model.Model;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.network.TVFetchrAsync;
import homepunk.lesson.first.network.TVNetworkParser;
import homepunk.lesson.first.presenter.Presenter;

import static homepunk.lesson.first.model.Model.Observerable;

public class TVObjectFetchrModel implements Model.TVObjectFetchrModel, Observerable {
    private TVFetchrAsync task;
    private String ref;
    private TVSeries tvSeries;
    private Presenter.Observer listener;

    @Override
    public void openHttpConnection() {
        task = (TVFetchrAsync) new TVFetchrAsync(new TVFetchrAsync.IResultListener() {
            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;
                try {
                        tvSeries = TVNetworkParser.getDetailedByJsonId(result);
                        notifyObservers();
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
//                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        }).execute(ref);
    }

    @Override
    public void setExecuteRef(String ref) {
        this.ref = ref;
    }

    @Override
    public void registerObserver(Presenter.Observer listener) {
        this.listener = listener;
    }

    @Override
    public void notifyObservers() {
        listener.update(tvSeries);
    }
}
