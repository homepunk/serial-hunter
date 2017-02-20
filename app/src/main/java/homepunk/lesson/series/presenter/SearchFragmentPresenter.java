package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        model.fetchOnAirSeries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Series>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(SearchFragmentPresenter.this.view != null)
                            SearchFragmentPresenter.this.view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Series> series) {
                        if(SearchFragmentPresenter.this.view != null)
                            SearchFragmentPresenter.this.view.onRecommendedSeriesRecieved(series);
                    }
                });
    }

    @Override
    public void getSearchResults(String searchString) {
        model.fetchSearchResults(searchString).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Series>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (SearchFragmentPresenter.this.view != null)
                            SearchFragmentPresenter.this.view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Series> series) {
                        if (SearchFragmentPresenter.this.view != null)
                            SearchFragmentPresenter.this.view.onSearchResultsRecieved(series);
                    }
                });
    }


    @Override
    public void onSearchViewClicked() {

    }
}
