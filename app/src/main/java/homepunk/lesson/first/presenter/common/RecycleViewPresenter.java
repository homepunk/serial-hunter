package homepunk.lesson.first.presenter.common;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.model.TVSeries;

public class RecycleViewPresenter implements Presenter.RecycleView {
    private Resources resources;
    private Presenter presenter;
    private RecyclerView recyclerView;
    private TVSeriesAdapter adapter;
    private Context context;
//    private TVListFetchrModel task;
    private RecyclerView.LayoutManager layoutManager;
    private List<TVSeries> tvList;
    private int quantity = 0;

    public void setResources(Resources resources){
        this.resources = resources;
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setTVList(List<TVSeries> tvList) {
        this.tvList = tvList;
    }

    @Override
    public void setAdapter(TVSeriesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void setUpRecycleView(RecyclerView rv) {
        if(rv == null)
            return;

        this.recyclerView = rv;
//        if(quantity == 0)
//        else tvList = new ArrayList<>(quantity);

        recyclerView.setAdapter(adapter);

        if(layoutManager != null)
            recyclerView.setLayoutManager(layoutManager);
        else
        recyclerView.setLayoutManager(new GridLayoutManager(context, resources.getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

    @Override
    public void setLayoutManeger(RecyclerView.LayoutManager layoutManeger) {
        this.layoutManager = layoutManeger;
    }

    @Override
    public void setItemsQuantity(int quantity) {
        this.quantity = quantity;
    }
}
