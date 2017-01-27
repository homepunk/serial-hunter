package homepunk.lesson.first.model.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import org.json.JSONException;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.TVSeriesDataManager;
import homepunk.lesson.first.model.Model;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.network.TVFetchrAsync;
import homepunk.lesson.first.network.TVNetworkParser;
import homepunk.lesson.first.presenter.Presenter;

public class TVListFetchrModel implements Model.TVListFetchrModel {
    private TVListAdapter adapter;
    private List<TVSeries> tvList;
    private TVFetchrAsync task;
    private Context context;
    private String ref;

    public TVListFetchrModel(Presenter viewPresenter) {
        this.context = viewPresenter.getContext();
    }

    @Override
    public void setTVList(List<TVSeries> tvList) {
        if(this.tvList != null)
            return;

        this.tvList = tvList;
    }

    @Override
    public void clearResults() {
        if (!tvList.isEmpty())
            tvList.clear();
    }

    @Override
    public void setAdapter(TVListAdapter adapter) {
        if(this.adapter != null)
            return;

        this.adapter = adapter;
    }

    @Override
    public void openHttpConnection() {
        final TVSeriesDataManager db = new TVSeriesDataManager(context);
        if (!isNetworkAvailable(context)) {
            List<TVSeries> dbMovies = db.getAll();
            if (dbMovies != null) {
                tvList.addAll(dbMovies);
                adapter.notifyDataSetChanged();
            }
        } else
        task = (TVFetchrAsync) new TVFetchrAsync(new TVFetchrAsync.IResultListener() {
            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;

                    try {
                            tvList.clear();
                        tvList.addAll(TVNetworkParser.getSearchedTVSeries(result));
                        db.saveAll(tvList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                adapter.notifyDataSetChanged();
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

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
