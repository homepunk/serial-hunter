package homepunk.lesson.first.model.network;

import android.text.TextUtils;

import org.json.JSONException;

import homepunk.lesson.first.model.Model;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.network.TVFetchrAsync;
import homepunk.lesson.first.network.TVNetworkParser;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;

public class TVObjectFetchrModel implements Model.TVObjectFetchrModel {
    private TVFetchrAsync task;
    private String ref;
    private TVSeries tvSeries;
    private DetailedFragmentPresenter viewPresenter;

    public TVObjectFetchrModel(Presenter viewPresenter) {
        this.viewPresenter = (DetailedFragmentPresenter) viewPresenter;
    }

    @Override
    public void openHttpConnection() {
        task = (TVFetchrAsync) new TVFetchrAsync(new TVFetchrAsync.IResultListener() {
            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;
                try {
                        tvSeries = TVNetworkParser.getDetailedByJsonId(result);
                        viewPresenter.update(tvSeries);
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
}
