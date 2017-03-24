package homepunk.lesson.series;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.lesson.series.data.database.RealmRepository;
import homepunk.lesson.series.data.rest.TmdbRepository;
import homepunk.lesson.series.modules.AppModule;
import homepunk.lesson.series.modules.DataModule;
import homepunk.lesson.series.modules.JsoupModule;
import homepunk.lesson.series.modules.PresentersModule;
import homepunk.lesson.series.ui.detailed.DetailedFragment;
import homepunk.lesson.series.ui.main.UpdatesFragment;
import homepunk.lesson.series.ui.main.MainActivity;
import homepunk.lesson.series.ui.main.MainFragment;
import homepunk.lesson.series.ui.main.PopularFragment;

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class, DataModule.class, JsoupModule.class})
public interface AppComponent{
    MainFragment plus(MainFragment fragment);

    UpdatesFragment plus(UpdatesFragment fragment);

    MainActivity plus(MainActivity activity);

    DetailedFragment plus(DetailedFragment fragment);

    PopularFragment plus(PopularFragment fragment);

    TmdbRepository plus(TmdbRepository repository);

    RealmRepository plus(RealmRepository repository);
}
