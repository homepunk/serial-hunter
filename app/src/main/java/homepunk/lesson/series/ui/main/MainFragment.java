package homepunk.lesson.series.ui.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dinuscxj.refresh.RecyclerRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.GridViewHolder;
import homepunk.lesson.series.adapter.SeriesAdapter;
import homepunk.lesson.series.data.Constants;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.ui.RecyclerClickListener;
import homepunk.lesson.series.ui.detailed.DetailedActivity;
import homepunk.lesson.series.utils.ScreenUtils;

public class MainFragment extends Fragment implements homepunk.lesson.series.interfaces.View.MainFragmentView, RecyclerRefreshLayout.OnRefreshListener   {
    @Inject Presenter.MainFragmentPresenter fragmentPresenter;

    @Bind(R.id.main_swipe) RecyclerRefreshLayout refreshLayout;
    @Bind(R.id.movies_rv) RecyclerView recycler;

    private List<Series> onAirSeries;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_spinner, menu);

        MenuItem item = menu.findItem(R.id.menu_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        setUpSpinner(spinner);
    }

    @Override
    public void onTVSeriesReceived(List<Series> seriesList) {
        this.onAirSeries.clear();
        this.onAirSeries.addAll(seriesList);
        Toast.makeText(getContext(), "Сериалы обновлены", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void initUI(ViewGroup root){
        App.getAppComponent(getContext()).plus(this);
        ButterKnife.bind(this, root);
        setUpRecycleView();
        initSwipeAndRefreshLayout();
        setHasOptionsMenu(true);
    }

    private void setUpRecycleView(){
        onAirSeries = new ArrayList<>(Constants.FILM_COUNT);

        adapter = new SeriesAdapter(getContext(), onAirSeries);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

        recycler.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemMotionEventListener() {
            @Override
            public void onItemClick(View view, int position) {
                startDetailedActivity(onAirSeries.get(position).getId());
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if(!isFavorite(view))
                    setFavorite(view);
                else setUnfavorite(view);
                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void startDetailedActivity(int id){
        Intent intent = new Intent(getContext(), DetailedActivity.class);

        intent.putExtra(Constants.KEY_ID, id);
        startActivity(intent);
    }

    private void setFavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star_selected);
        holder.setFavorite(true);
    }

    private void setUnfavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star_full);
        holder.setFavorite(false);
    }

    private boolean isFavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        return holder.isFavorite() ? true : false;
    }

    private void initSwipeAndRefreshLayout(){
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED);
        refreshLayout.setRefreshInitialOffset(30);
    }

    private void setUpSpinner(Spinner spinner) {
        int spinnerWidth = ScreenUtils.getDisplayContentWidth(getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_spinner, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
        spinner.setDropDownWidth(spinnerWidth);
        spinner.setAdapter(adapter);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                fragmentPresenter.getOnAirSeries();
                refreshLayout.setRefreshing(false);
            }
        }, 1500);
    }

    public List<Series> getSerieslist(){
        return this.onAirSeries;
    }
}

