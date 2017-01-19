package homepunk.lesson.first.model;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONException;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;

public class TVFetchrAsyncModel implements Model.TVFetchrAsyncModel {
    private Context context;
    private TVListAdapter adapter;
    private List<TVSeries> tvList;
    private TVSeries tvSeries;
    private DetailedFragmentPresenter presenter;
    private TVFetchrAsync task;

    public TVFetchrAsyncModel(Context context, DetailedFragmentPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    public TVFetchrAsyncModel(Context context) {
        this.context = context;
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

    @Override
    public void execute(String ref) {
        task.execute(ref);
    }
}
