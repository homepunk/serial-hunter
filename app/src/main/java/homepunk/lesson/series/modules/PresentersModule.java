package homepunk.lesson.series.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.presenter.DetailedViewPresenter;
import homepunk.lesson.series.presenter.MainViewPresenter;
import homepunk.lesson.series.presenter.PopularViewPresenter;
import homepunk.lesson.series.presenter.SearchViewPresenter;
import homepunk.lesson.series.presenter.UpdatesPresenter;

@Module
public class PresentersModule {
    @Provides
    @Singleton
    Presenter.MainPresenter provideMainFragmentPresenter(Model.DataManager model) {
        return new MainViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.SearchPresenter provideSearchFragmentPresenter(Model.DataManager model){
        return new SearchViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.DetailedPresenter provideDetailedFragmentPresenter(Model.DataManager model){
        return new DetailedViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.PopularPresenter provideTopRatedFragmentPresenter(Model.DataManager model){
        return new PopularViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.UpdatesPresenter provideUpdatesPresenter(Model.HdrezkaParseManager parser, Model.DataManager dataManager){
        return new UpdatesPresenter(parser, dataManager);
    }
}
