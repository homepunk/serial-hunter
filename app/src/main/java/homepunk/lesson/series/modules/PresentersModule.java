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

@Module
public class PresentersModule {
    @Provides
    @Singleton
    Presenter.MainPresenter provideMainFragmentPresenter(Model.DataManagerModel model) {
        return new MainViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.SearchPresenter provideSearchFragmentPresenter(Model.DataManagerModel model){
        return new SearchViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.DetailedPresenter provideDetailedFragmentPresenter(Model.DataManagerModel model){
        return new DetailedViewPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.PopularPresenter provideTopRatedFragmentPresenter(Model.DataManagerModel model){
        return new PopularViewPresenter(model);
    }
}
