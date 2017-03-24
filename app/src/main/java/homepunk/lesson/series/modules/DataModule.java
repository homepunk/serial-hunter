package homepunk.lesson.series.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.series.data.database.RealmRepository;
import homepunk.lesson.series.data.database.RealmService;
import homepunk.lesson.series.data.rest.TmdbApi;
import homepunk.lesson.series.data.rest.TmdbRepository;
import homepunk.lesson.series.interfaces.Model;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static homepunk.lesson.series.data.Constants.BASE_URL;

@Module
public class DataModule {
    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    public TmdbApi provideTmdbApi(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(TmdbApi.class);
    }

    @Provides
    @Singleton
    public TmdbRepository provideTmdbRepository(Context context) {
        return new TmdbRepository(context);
    }

    @Provides
    @Singleton
    public Model.DataManager provideDataManager(TmdbRepository retrofitRepository, RealmRepository realmRepository) {
        return new homepunk.lesson.series.data.DataManager(retrofitRepository, realmRepository);
    }


    @Provides
    @Singleton
    RealmService provideRealmService(){
        return new RealmService();
    }

    @Provides
    @Singleton
    RealmRepository provdieRealmRepository(Context context){
        return new RealmRepository(context);
    }
}
