package homepunk.lesson.first.dagger_modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.data.database.TVSeriesOpenHelper;
import homepunk.lesson.first.data.local.SharedPrefencesStorage;
import homepunk.lesson.first.data.network.NetworkStorage;
import homepunk.lesson.first.interfaces.Model;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    public Model.TVSeriesModel provideDataRepository(Context context){
        return new DataRepository(context);
    }

    @Provides
    @Singleton
    public DatabaseStorage provideDatabaseStorage() {
        return new DatabaseStorage();
    }

    @Provides
    @Singleton
    public NetworkStorage provideNetworkStorage(Context context){
        return new NetworkStorage(context);
    }

    @Provides
    @Singleton
    public SharedPrefencesStorage provideSharedPrefencesStorage(Context context){
     return new SharedPrefencesStorage(context);
    }

    @Provides
    @Singleton
    public TVSeriesOpenHelper provideTVSeriesOpenHelper(Context context){
        return new TVSeriesOpenHelper(context);
    }
}
