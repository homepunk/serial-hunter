package homepunk.lesson.first.view.main;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.adapter.TVSeriesAdapter;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.data.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.presenter.main.MainFragmentPresenter;

public class MainFragment extends Fragment implements homepunk.lesson.first.interfaces.View.MainFragmentView {
    @Bind(R.id.movies_rv) RecyclerView recycler;

    private Presenter.MainFragmentPresenter fragmentPresenter;
    private List<TVSeries> tvSeries;
    private TVSeriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        initUI(root);
        fragmentPresenter = new MainFragmentPresenter(getContext());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentPresenter.setView(this);
        fragmentPresenter.getMostPopularSeries();
    }

    @Override
    public void onTVSeriesReceived(List<TVSeries> tvSeries) {
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
        tvSeries = new ArrayList<>(Constants.FILM_COUNT);
        adapter = new TVSeriesAdapter(getContext(), tvSeries);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));

        recycler.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemMotionEventListener() {
            @Override
            public void onItemClick(View view, int position) {
                fragmentPresenter.onSeriesSelected(tvSeries.get(position).id);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long click", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}

