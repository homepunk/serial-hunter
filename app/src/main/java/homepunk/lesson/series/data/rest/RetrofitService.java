package homepunk.lesson.series.data.rest;

import homepunk.lesson.series.model.Page;
import homepunk.lesson.series.model.Series;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
        @GET("tv/{id}")
        Observable<Series> loadTVSeriesDetails(@Path("id") int id,
                                         @Query("language") String language,
                                         @Query("api_key") String apiKey);

        @GET("tv/on_the_air")
        Observable<Page> loadOnAirSeries(@Query("page") int page,
                                         @Query("language") String language,
                                         @Query("api_key") String apiKey);

        @GET("tv/popular")
        Observable<Page> loadPopularSeries(@Query("page") int page,
                                     @Query("language") String language,
                                     @Query("api_key") String apiKey);

        @GET("search/tv")
        Observable<Page> loadSearchResults(@Query("page") int page,
                                     @Query("query") String query,
                                     @Query("language") String language,
                                     @Query("api_key") String apiKey);
}
