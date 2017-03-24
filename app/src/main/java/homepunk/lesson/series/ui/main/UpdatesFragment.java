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
import homepunk.lesson.series.adapter.SeriesRecyclerAdapter;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View.UpdatesView;
import homepunk.lesson.series.model.HdrezkaSeries;
import homepunk.lesson.series.model.Series;

public class UpdatesFragment extends Fragment implements UpdatesView {
    @Inject
    Presenter.UpdatesPresenter updatesPresenter;

    @Bind(R.id.last_updates_rv)
    RecyclerView recyclerView;

    private List<Series> series;
    private List<HdrezkaSeries> updates;
    private SeriesRecyclerAdapter updatesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_updates, container, false);
        App.getAppComponent(getContext()).plus(this);
        ButterKnife.bind(this, root);

        setupRecycleView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        updatesPresenter.setView(this);
        updatesPresenter.getUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        updatesPresenter.unsuscribeFromObservable();
    }

    @Override
    public void onResult(Series series, HdrezkaSeries updateInfo) {
        this.series.add(series);
        this.updates.add(updateInfo);
        updatesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void setupRecycleView() {
        series = new ArrayList<>();
        updates = new ArrayList<>();
        updatesAdapter = new SeriesRecyclerAdapter(getContext(), series, updates);

        recyclerView.setAdapter(updatesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
