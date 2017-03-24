package homepunk.lesson.series.data.parser;

import java.util.List;

import homepunk.lesson.series.model.HdrezkaSeries;
import retrofit2.http.GET;
import rx.Observable;

public interface HdrezkaApi {
    public static final String HDREZKA_BASE_URL = "http://hdrezka.me/";

    @GET(".")
    Observable<List<HdrezkaSeries>> getUpdates();
}
