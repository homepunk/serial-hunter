package homepunk.lesson.first.presenter;

import java.util.List;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.Series;

public class SearchFragmentPresenter implements Presenter.SearchFragmentPresenter {
    private final Model.DataRepositoryModel model;
    private View.SearchFragmentView view;

    public SearchFragmentPresenter(Model.DataRepositoryModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.SearchFragmentView view) {
        this.view = view;
    }

    @Override
    public void getRecommendedSeries() {
        model.fetchOnAirSeries(new Listeners.RetrofitListListener() {
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
    public void onSearchViewClicked() {

    }
}
