package homepunk.lesson.series.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.SeriesAdapter;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View.HotUpdatesFragmentView;
import homepunk.lesson.series.model.Series;

public class TopRatedFragment extends Fragment implements HotUpdatesFragmentView {
    @Inject Presenter.TopRatedFragmentPresenter fragmentPresenter;

    @Bind(R.id.rv_top_rated) RecyclerView rvTopRated;

    private List<Series> seriesList;
    private SeriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_rated, container, false);
        initUi(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        fragmentPresenter.setView(this);
        fragmentPresenter.getHotUpdates();
    }

    @Override
    public void onTopRatedRecieved(List<Series> seriesList) {
        this.seriesList.clear();
        this.seriesList.addAll(seriesList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void initUi(View root) {
        ButterKnife.bind(this, root);
        App.getAppComponent(getContext()).plus(this);

        seriesList = new ArrayList<>();
        adapter = new SeriesAdapter(getContext(), seriesList);

        rvTopRated.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopRated.setAdapter(adapter);
    }
}
