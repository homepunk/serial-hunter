package homepunk.lesson.first.di;

import javax.inject.Singleton;

import dagger.Component;
import homepunk.lesson.first.ui.detailed.DetailedFragment;
import homepunk.lesson.first.ui.main.MainActivity;
import homepunk.lesson.first.ui.main.MainFragment;
import homepunk.lesson.first.ui.search.SearchFragment;

@Singleton
@Component(modules = {AppModule.class, PresentersModule.class})
public interface AppComponent{
    MainActivity plus(MainActivity activity);

    MainFragment plus(MainFragment fragment);

    SearchFragment plus(SearchFragment fragment);

    DetailedFragment plus(DetailedFragment fragment);
}
