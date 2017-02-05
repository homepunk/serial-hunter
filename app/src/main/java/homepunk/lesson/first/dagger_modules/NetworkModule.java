package homepunk.lesson.first.dagger_modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.data.database.SeriesOpenHelper;
import homepunk.lesson.first.interfaces.Model;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Model.DataRepositoryModel provideDataRepository(Context context){
        return new DataRepository(context);
    }

    @Provides
    @Singleton
    public DatabaseStorage provideDatabaseStorage(Context context) {
        return new DatabaseStorage(context);
    }

    @Provides
    @Singleton
    public SeriesOpenHelper provideTVSeriesOpenHelper(Context context){
        return new SeriesOpenHelper(context);
    }
}
