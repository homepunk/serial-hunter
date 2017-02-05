package homepunk.lesson.first;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.lesson.first.dagger_modules.AppModule;
import homepunk.lesson.first.dagger_modules.NetworkModule;
import homepunk.lesson.first.dagger_modules.PresentersModule;
import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.data.database.DatabaseStorage;
import homepunk.lesson.first.ui.detailed.DetailedFragment;
import homepunk.lesson.first.ui.main.MainFragment;
import homepunk.lesson.first.ui.search.SearchFragment;

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class, NetworkModule.class})
public interface AppComponent{
    MainFragment plus(MainFragment fragment);

    SearchFragment plus(SearchFragment fragment);

    DetailedFragment plus(DetailedFragment fragment);

    DataRepository plus(DataRepository repository);

    DatabaseStorage plus(DatabaseStorage storage);
}
