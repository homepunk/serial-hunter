package homepunk.lesson.first.model;

import android.text.TextUtils;

import org.json.JSONException;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;
import homepunk.lesson.first.presenter.main.SearchPresenter;

public class TVFetchrAsyncModel implements Model.TVFetchrAsyncModel {
    private TVListAdapter adapter;
    private List<TVSeries> tvList;
    private TVSeries tvSeries;
    private DetailedFragmentPresenter detailedPresenter;
    private SearchPresenter searchPresenter;
    private TVFetchrAsync task;

    public TVFetchrAsyncModel(DetailedFragmentPresenter presenter) {
        this.detailedPresenter = presenter;
    }

    public TVFetchrAsyncModel(SearchPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public TVFetchrAsyncModel() {
    }

    @Override
    public void makeHttpConnection() {
        task = new TVFetchrAsync(new TVFetchrAsync.IResultListener() {
            @Override
            public void onResult(String result) {
                tvList.clear();
                if (TextUtils.isEmpty(result))
                    return;
                try {
                    if (searchPresenter != null){
                        tvList.clear();
                        tvList.addAll(TVNetworkParser.getSearchedTVSeries(result));
                    } else if (detailedPresenter != null) {
                        tvSeries = TVNetworkParser.getDetailedByJsonId(result);
                        detailedPresenter.update(tvSeries);
                    } else if (tvList != null) {
                        tvList.clear();
                        tvList.addAll(TVNetworkParser.getTVSeriesFromJson(result));
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
//        execute("https://api.themoviedb.org/3/search/tv?page=1&query=lost&language=en-US&api_key=515e5bce899580aada662ed279a9a94b");
        execute(Constants.TV_SEARCH + title + Constants.LANGUAGE_EN + Constants.API_KEY);
    }

    @Override
    public void execute(String ref) {
        task.execute(ref);
    }
}
