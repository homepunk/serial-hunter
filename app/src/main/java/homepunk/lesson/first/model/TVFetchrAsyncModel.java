package homepunk.lesson.first.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONException;

import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;
import homepunk.lesson.first.presenter.Presenter.TVFetchAsyncPresenter;

public class TVFetchrAsyncModel implements Model.TVFetchrAsyncModel {
    private Context context;
    private TVListAdapter adapter;
    private List<TVSeries> tvList;
    private TVFetchAsyncPresenter taskPresenter;
    private TVFetchrAsync task;

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
                    if(tvList != null) {
                        tvList.addAll(TVNetworkParser.getFilmsFromJson(result));
                    } else onError("LIST IS NULL");
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                } finally {
                    if(adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
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
