package homepunk.lesson.series.presenter;

import java.util.List;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.interfaces.View;
import homepunk.lesson.series.model.Series;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


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
        model.fetchOnAirSeries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Series>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                       if(MainFragmentPresenter.this.view != null)
                           MainFragmentPresenter.this.view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Series> series) {
                        if(MainFragmentPresenter.this.view != null)
                            MainFragmentPresenter.this.view.onTVSeriesReceived(series);
                    }
                });
    }

    @Override
    public void onSeriesSelected(int id) {
    }
}
