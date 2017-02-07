package homepunk.lesson.series.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.series.data.DataManager;
import homepunk.lesson.series.data.database.DbFlowService;
import homepunk.lesson.series.data.rest.RetrofitService;
import homepunk.lesson.series.interfaces.Model;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static homepunk.lesson.series.data.Constants.BASE_URL;
import static homepunk.lesson.series.data.Constants.WITHOUT_EXPOSE;
import static homepunk.lesson.series.data.Constants.WITH_EXPOSE;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Model.DataManagerModel provideDataManager(Context context){
        return new DataManager(context);
    }

    @Provides
    @Singleton
    public DbFlowService provideDbFlowService(){
        return new DbFlowService();
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    @Singleton
    @Named(WITH_EXPOSE)
    public RetrofitService provideTmdbApiWithExpose(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    @Singleton
    @Named(WITHOUT_EXPOSE)
    public RetrofitService provideTmdbApiWithoutExpose(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
