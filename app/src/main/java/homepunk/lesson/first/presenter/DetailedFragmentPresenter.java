package homepunk.lesson.first.presenter;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.Series;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private View.DetailedFragmentView view;
    private final Model.DataRepositoryModel model;

    public DetailedFragmentPresenter(Model.DataRepositoryModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getSeriesDescriptionById(int id) {
        model.fetchSeriesById(id, new Listeners.RetrofitListener() {
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

