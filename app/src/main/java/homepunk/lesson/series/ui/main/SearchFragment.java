package homepunk.lesson.series.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View.SearchFragmentView;
import homepunk.lesson.series.model.Series;

public class SearchFragment extends Fragment implements SearchFragmentView {
    @Inject
    Presenter.SearchPresenter searchPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        App.getAppComponent(getContext()).plus(this);
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        searchPresenter.setView(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        searchPresenter.unsuscribeFromObservable();
    }

    @Override
    public void onResult(List<Series> seriesList) {
    }

    @Override
    public void onSearchResult(List<Series> seriesList) {
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

}
