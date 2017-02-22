package homepunk.lesson.series.utils;

import java.util.List;

import homepunk.lesson.series.model.Page;
import homepunk.lesson.series.model.Series;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable.Transformer<Page, List<Series>> getSeriesWithViewType(int viewType){
        return observable -> observable.map(page -> {
            page.setViewType(viewType);
            return page.getResults();
        });
    }
}
