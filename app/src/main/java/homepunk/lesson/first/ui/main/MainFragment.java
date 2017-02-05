package homepunk.lesson.first.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import homepunk.lesson.first.adapter.TvSeriesAdapter;
import homepunk.lesson.first.App;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.data.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.model.SeriesResponse;
import homepunk.lesson.first.model.Series;
import homepunk.lesson.first.ui.detailed.DetailedActivity;

public class MainFragment extends Fragment implements homepunk.lesson.first.interfaces.View.MainFragmentView {
    @Inject Presenter.MainFragmentPresenter fragmentPresenter;

    @Bind(R.id.movies_rv) RecyclerView recycler;

    private List<Series> tvSeries;
    private List<SeriesResponse> onAirList;
    private TvSeriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        initUI(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentPresenter.setView(this);
        fragmentPresenter.getOnAirSeries();
    }

    @Override
    public void onTVSeriesReceived(List<Series> tvSeries) {
        this.tvSeries.clear();
        this.tvSeries.addAll(tvSeries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void initUI(ViewGroup root){
        ButterKnife.bind(this, root);
        App.getAppComponent(getContext()).plus(this);

        tvSeries = new ArrayList<>(Constants.FILM_COUNT);

        adapter = new TvSeriesAdapter(getContext(), tvSeries);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

        recycler.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemMotionEventListener() {
            @Override
            public void onItemClick(View view, int position) {
                openDescription(tvSeries.get(position).getId());
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void openDescription(int id){
        Intent intent = new Intent(getContext(), DetailedActivity.class);

        intent.putExtra(Constants.KEY_ID, id);
        startActivity(intent);
    }
}

