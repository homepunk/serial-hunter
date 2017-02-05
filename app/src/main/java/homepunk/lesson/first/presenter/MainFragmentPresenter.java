package homepunk.lesson.first.presenter;

import java.util.List;

import homepunk.lesson.first.interfaces.Listeners;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;
import homepunk.lesson.first.model.Series;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter{
    private final Model.DataRepositoryModel model;
    private View.MainFragmentView view;

    public MainFragmentPresenter(Model.DataRepositoryModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getOnAirSeries(){
        model.fetchOnAirSeries(new Listeners.RetrofitListListener() {
            @Override
            public void onResult(List<Series> onAirList) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onTVSeriesReceived(onAirList);
            }

            @Override
            public void onError(String e) {
                if (MainFragmentPresenter.this.view != null)
                    MainFragmentPresenter.this.view.onError(e);
            }
        });
    }

    @Override
    public void onSeriesSelected(int id) {
    }
}
