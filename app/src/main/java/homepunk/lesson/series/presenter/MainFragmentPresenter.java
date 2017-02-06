package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Listeners.RetrofitListener;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;


public class MainFragmentPresenter implements Presenter.MainFragmentPresenter{
    private final Model.DataManagerModel model;
    private View.MainFragmentView view;

    public MainFragmentPresenter(Model.DataManagerModel model) {
        this.model = model;
    }

    @Override
    public void setView(View.MainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getOnAirSeries(){
        model.fetchOnAirSeries(new RetrofitListener<List<Series>>() {
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
