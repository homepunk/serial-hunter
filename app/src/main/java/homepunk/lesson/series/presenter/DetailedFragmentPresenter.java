package homepunk.lesson.series.presenter;

import homepunk.lesson.series.interfaces.Listeners;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private View.DetailedFragmentView view;
    private final Model.DataManagerModel model;

    public DetailedFragmentPresenter(Model.DataManagerModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getSeriesDescriptionById(int id) {
        model.fetchSeriesById(id, new Listeners.RetrofitListener<Series>() {
            @Override
            public void onResult(Series series) {
                if (DetailedFragmentPresenter.this.view != null)
                    DetailedFragmentPresenter.this.view.onSeriesDescRecieved(series);
            }

            @Override
            public void onError(String e) {
                if(DetailedFragmentPresenter.this.view != null)
                    DetailedFragmentPresenter.this.view.onError(e);
            }
        });
    }

}

