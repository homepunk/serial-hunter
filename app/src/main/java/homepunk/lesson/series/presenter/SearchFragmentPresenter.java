package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Listeners;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;

public class SearchFragmentPresenter implements Presenter.SearchFragmentPresenter {
    private final Model.DataManagerModel model;
    private View.SearchFragmentView view;

    public SearchFragmentPresenter(Model.DataManagerModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.SearchFragmentView view) {
        this.view = view;
    }

    @Override
    public void getSearchRecommendationResults() {
        model.fetchOnAirSeries(new Listeners.RetrofitListener<List<Series>>() {
            @Override
            public void onResult(List<Series> onAirList) {
                if (SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onRecommendedSeriesRecieved(onAirList);
            }

            @Override
            public void onError(String e) {
                if (SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onError(e);
            }
        });
    }

    @Override
    public void getSearchResults(String searchString) {
        model.fetchSearchResults(searchString, new Listeners.RetrofitListener<List<Series>>() {
            @Override
            public void onResult(List<Series> seriesList) {
                if(SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onSearchResultsRecieved(seriesList);
            }

            @Override
            public void onError(String e) {
                if(SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onError(e);
            }
        });
    }

    @Override
    public void onSearchViewClicked() {

    }
}
