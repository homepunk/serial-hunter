package homepunk.lesson.first.presenter.common;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.network.TVListFetchrModel;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.View;

public class RecycleViewPresenter implements Presenter.RecycleView {
    private View view;
    private RecyclerView recyclerView;
    private TVListAdapter adapter;
    private Context context;
    private TVListFetchrModel task;
    private RecyclerView.LayoutManager layoutManager;
    private List<TVSeries> tvList;
    private int quantity = 0;

    public RecycleViewPresenter(View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void attachRecycleView(RecyclerView rv) {
        if(rv == null)
            return;

        this.recyclerView = rv;
        if(quantity == 0)
            tvList = new ArrayList<>(Constants.FILM_COUNT);
        else tvList = new ArrayList<>(quantity);

        adapter = new TVListAdapter(view.getContext(), tvList);
        recyclerView.setAdapter(adapter);

        if(layoutManager != null)
            recyclerView.setLayoutManager(layoutManager);
        else
        recyclerView.setLayoutManager(new GridLayoutManager(context, view.getResources().getConfiguration().orientation ==
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

    @Override
    public void updateContent(TVListFetchrModel fetchr) {
        this.task = fetchr;
        task.setTVList(tvList);
        task.clearResults();
        task.setAdapter(adapter);
        task.openHttpConnection();
    }

}
