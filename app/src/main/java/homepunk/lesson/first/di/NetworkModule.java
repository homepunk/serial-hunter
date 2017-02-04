package homepunk.lesson.first.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.interfaces.Model;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Model.TVSeriesModel provideDataRepository(Context context){
        return new DataRepository(context);
    }
}
