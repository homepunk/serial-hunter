package homepunk.lesson.series;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.lesson.series.data.database.RealmRepository;
import homepunk.lesson.series.data.rest.TmdbRepository;
import homepunk.lesson.series.modules.AppModule;
import homepunk.lesson.series.modules.DataModule;
import homepunk.lesson.series.modules.PresentersModule;
import homepunk.lesson.series.ui.detailed.DetailedFragment;
import homepunk.lesson.series.ui.main.MainFragment;
import homepunk.lesson.series.ui.main.PopularFragment;
import homepunk.lesson.series.ui.main.SearchFragment;

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class, DataModule.class})
public interface AppComponent{
    MainFragment plus(MainFragment fragment);

    SearchFragment plus(SearchFragment fragment);

    DetailedFragment plus(DetailedFragment fragment);

    PopularFragment plus(PopularFragment fragment);

    TmdbRepository plus(TmdbRepository repository);

    RealmRepository plus(RealmRepository repository);
}
