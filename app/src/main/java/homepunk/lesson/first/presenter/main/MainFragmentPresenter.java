package homepunk.lesson.first.presenter.main;

import android.content.Context;

import java.util.List;

import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.interfaces.Listener;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.TVSeries;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter{
    private View.MainFragmentView view;
    private final Model.TVSeriesModel model;

    public MainFragmentPresenter(Context context) {
        this.model = new DataRepository(context);
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getMostPopularTVSeries() {
        model.fetchTVSeries(new Listener() {
            @Override
            public void onResult(List<TVSeries> tvSeries) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onTVSeriesReceived(tvSeries);
            }

            @Override
            public void onError(Exception e) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onError(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onTVSeriesSelected(int id) {

    }
}
