package homepunk.lesson.series.ui.main;

import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.adapter.GridViewHolder;
import homepunk.lesson.series.adapter.SeriesRecyclerAdapter;
import homepunk.lesson.series.data.Constants;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.ui.RecyclerClickListener;
import homepunk.lesson.series.utils.DisplayUtils;

import static homepunk.lesson.series.utils.NavigationUtils.navigateToDetailed;

public class MainFragment extends Fragment implements homepunk.lesson.series.interfaces.View.MainFragmentView {
    @Inject Presenter.MainPresenter fragmentPresenter;

    @Bind(R.id.movies_rv) RecyclerView recycler;

    private List<Series> onAirSeries;
    private SeriesRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
//        setHasOptionsMenu(true);
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
    public void onDestroy() {
        super.onDestroy();

        fragmentPresenter.unsuscribeFromObservable();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_spinner, menu);

        MenuItem item = menu.findItem(R.id.menu_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

//        setupSpinner(spinner);
    }

    @Override
    public void onResult(List<Series> seriesList) {
        this.onAirSeries.clear();
        this.onAirSeries.addAll(seriesList);
        adapter.notifyDataSetChanged();

        Toast.makeText(getContext(), "Сериалы обновлены", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void initUI(View root){
        App.getAppComponent(getContext()).plus(this);
        ButterKnife.bind(this, root);

        setupRecycleView();
    }

    private void setupRecycleView(){
        onAirSeries = new ArrayList<>(Constants.FILM_COUNT);

        adapter = new SeriesRecyclerAdapter(getContext(), onAirSeries);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

        recycler.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemMotionEventListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = onAirSeries.get(position).getId();
                navigateToDetailed(getContext(), id);
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

    private void setupSpinner(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item_spinner_title, Constants.data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);

        spinner.setAdapter(adapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.colorText), PorterDuff.Mode.SRC_ATOP);
        spinner.setDropDownWidth(DisplayUtils.getDisplayContentWidth(getContext()));
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

    private void setFavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star_selected);
        holder.setFavorite(true);
    }

    private void setUnfavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        holder.getFavorite().setImageResource(R.drawable.ic_star);
        holder.setFavorite(false);
    }

    private boolean isFavorite(View view){
        GridViewHolder holder = new GridViewHolder(view);
        return holder.isFavorite() ? true : false;
    }
}

