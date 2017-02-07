package homepunk.lesson.series.ui.main;

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
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.SeriesAdapter;
import homepunk.lesson.series.adapter.SeriesAdapter.GridViewHolder;
import homepunk.lesson.series.data.Constants;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.model.SeriesResponse;
import homepunk.lesson.series.ui.RecyclerClickListener;
import homepunk.lesson.series.ui.detailed.DetailedActivity;

public class MainFragment extends Fragment implements homepunk.lesson.series.interfaces.View.MainFragmentView {
    @Inject Presenter.MainFragmentPresenter fragmentPresenter;

    @Bind(R.id.movies_rv) RecyclerView recycler;

    private List<Series> tvSeries;
    private List<SeriesResponse> onAirList;
    private SeriesAdapter adapter;
    private boolean favorite;

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

        adapter = new SeriesAdapter(getContext(), tvSeries);
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
                if(!isFavorite(view))
                    setSelected(view);
                else setUnselected(view);
                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void openDescription(int id){
        Intent intent = new Intent(getContext(), DetailedActivity.class);

        intent.putExtra(Constants.KEY_ID, id);
        startActivity(intent);
    }

    private void setSelected(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star_selected);
        holder.setFavorite(true);
    }

    private void setUnselected(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star_full);
        holder.setFavorite(false);
    }

    private boolean isFavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        return holder.isFavorite() ? true : false;
    }
}

