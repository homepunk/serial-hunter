package homepunk.lesson.first.presenter;

import java.util.List;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.TVSeries;

public class SearchFragmentPresenter implements Presenter.SearchFragmentPresenter {
    private final Model.TVSeriesModel model;
    private View.SearchFragmentView view;

    public SearchFragmentPresenter(Model.TVSeriesModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.SearchFragmentView view) {
        this.view = view;
    }

    @Override
    public void getRecommendedSeries() {
        model.fetchSeriesList(new Listeners.ListListener() {
            @Override
            public void onResult(List<TVSeries> tvSeries) {
                if (SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onRecommendedSeriesRecieved(tvSeries);
            }

            @Override
            public void onError(Exception e) {
                if (SearchFragmentPresenter.this.view != null)
                    SearchFragmentPresenter.this.view.onError(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onSearchViewClicked() {

    }
}
