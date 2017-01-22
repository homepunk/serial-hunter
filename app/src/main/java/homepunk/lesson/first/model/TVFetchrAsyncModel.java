package homepunk.lesson.first.model;

import android.text.TextUtils;

import org.json.JSONException;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;

public class TVFetchrAsyncModel implements Model.TVFetchrAsyncModel {
    private TVListAdapter adapter;
    private List<TVSeries> tvList;
    private TVSeries tvSeries;
    private DetailedFragmentPresenter presenter;
    private TVFetchrAsync task;

    public TVFetchrAsyncModel(DetailedFragmentPresenter presenter) {
        this.presenter = presenter;
    }

    public TVFetchrAsyncModel() {
    }

    @Override
    public void makeHttpConnection() {
        task = new TVFetchrAsync(new TVFetchrAsync.IResultListener() {

            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;
                try {
                    if (tvList != null) {
                        tvList.addAll(TVNetworkParser.getFilmsFromJson(result));
                    } else if (presenter != null) {
                        tvSeries = TVNetworkParser.getDetailedByJsonId(result);
                        presenter.update(tvSeries);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String error) {
//                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setTVList(List<TVSeries> tvList) {
        this.tvList = tvList;
    }

    @Override
    public void setAdapter(TVListAdapter adapter) {
        this.adapter = adapter;
    }

    public void searchByTitle(String title){
        execute(Constants.TV_SERCH + title + Constants.LANGUAGE_EN + Constants.API_KEY);
    }

    @Override
    public void execute(String ref) {
        task.execute(ref);
    }
}
