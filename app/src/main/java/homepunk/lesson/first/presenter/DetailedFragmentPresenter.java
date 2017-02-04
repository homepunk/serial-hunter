package homepunk.lesson.first.presenter;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.TVSeries;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private View.DetailedFragmentView view;
    private final Model.TVSeriesModel model;

    public DetailedFragmentPresenter(Model.TVSeriesModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getSeriesDescriptionById(int id) {
        model.fetchSeriesById(id, new Listeners.Listener() {
            @Override
            public void onResult(TVSeries series) {
                if (DetailedFragmentPresenter.this.view != null)
                    DetailedFragmentPresenter.this.view.onSeriesDescRecieved(series);
            }

            @Override
            public void onError(Exception e) {
                if(DetailedFragmentPresenter.this.view != null)
                    DetailedFragmentPresenter.this.view.onError(e.getLocalizedMessage());
            }
        });
    }

}
