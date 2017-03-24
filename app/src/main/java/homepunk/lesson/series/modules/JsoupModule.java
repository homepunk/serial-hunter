package homepunk.lesson.series.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.series.data.parser.HdrezkaApi;
import homepunk.lesson.series.data.parser.HdrezkaParseManager;
import homepunk.lesson.series.interfaces.Model;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jsoup.JsoupConverterFactory;

import static homepunk.lesson.series.data.parser.HdrezkaApi.HDREZKA_BASE_URL;

@Module
public class JsoupModule {
    @Singleton
    @Provides
    public HdrezkaApi provideHdrezkaApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HDREZKA_BASE_URL)
                .addConverterFactory(JsoupConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(HdrezkaApi.class);
    }

    @Singleton
    @Provides
    public Model.HdrezkaParseManager provideHdrezkaRepository(HdrezkaApi hdrezkaApi){
        return new HdrezkaParseManager(hdrezkaApi);
    }

}
