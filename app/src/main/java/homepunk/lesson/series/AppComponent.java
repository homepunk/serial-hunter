package homepunk.lesson.series;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.lesson.series.data.DataManager;
import homepunk.lesson.series.data.database.DbFlowRepository;
import homepunk.lesson.series.data.rest.RetrofitRepository;
import homepunk.lesson.series.modules.AppModule;
import homepunk.lesson.series.modules.NetworkModule;
import homepunk.lesson.series.modules.PresentersModule;
import homepunk.lesson.series.ui.detailed.DetailedFragment;
import homepunk.lesson.series.ui.main.MainFragment;
import homepunk.lesson.series.ui.main.PopularFragment;
import homepunk.lesson.series.ui.main.SearchFragment;

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class, NetworkModule.class})
public interface AppComponent{
    MainFragment plus(MainFragment fragment);

    SearchFragment plus(SearchFragment fragment);

    DetailedFragment plus(DetailedFragment fragment);

    DataManager plus(DataManager repository);

    PopularFragment plus(PopularFragment fragment);

    RetrofitRepository plus(RetrofitRepository repository);

    DbFlowRepository plus(DbFlowRepository repository);
}
