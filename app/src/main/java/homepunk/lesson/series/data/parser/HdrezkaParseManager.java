package homepunk.lesson.series.data.parser;

import java.util.List;
import java.util.concurrent.TimeUnit;

import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.model.HdrezkaSeries;
import rx.Observable;
import rx.functions.Func1;

public class HdrezkaParseManager implements Model.HdrezkaParseManager {
    private HdrezkaApi hdrezkaApi;

    public HdrezkaParseManager(HdrezkaApi hdrezkaApi) {
        this.hdrezkaApi = hdrezkaApi;
    }

    @Override
    public Observable<HdrezkaSeries> fetchUpdates() {

      return hdrezkaApi.getUpdates().flatMap(new Func1<List<HdrezkaSeries>, Observable<HdrezkaSeries>>() {
          @Override
          public Observable<HdrezkaSeries> call(List<HdrezkaSeries> hdrezkaSeries) {
              return Observable.from(hdrezkaSeries)
                      .zipWith(Observable.interval(10, TimeUnit.MICROSECONDS), ((hdrezkaSeries1, aLong) -> hdrezkaSeries1));
          }
      });
    }
}
