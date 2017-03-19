package homepunk.lesson.series.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.adapter.SearchResultsAdapter;
import homepunk.lesson.series.interfaces.View.SearchFragmentView;
import homepunk.lesson.series.model.Series;

public class SearchFragment extends Fragment implements SearchFragmentView {

    private SearchResultsAdapter searchAdapter;
    private List<Series> searchResults;
    private String[] columns = new String[]{"_id", "SERIES_POSTER", "SERIES_TITLE"};
    private String searchHint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

//        App.getAppComponent(getContext()).plus(this);
//        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

//        searchPresenter.setView(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_main, menu);
//        MenuItem item =  menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) item.getActionView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        searchPresenter.unsuscribeFromObservable();
    }

    @Override
    public void onSearchResult(List<Series> seriesList) {
//        MatrixCursor matrixCursor = convertToCursor(searchResults, columns);
//        searchAdapter.changeCursor(matrixCursor);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void setupSearchView(SearchView searchView){

    }
}
