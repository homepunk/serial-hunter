package homepunk.lesson.series.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.series.interfaces.Model;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.presenter.DetailedFragmentPresenter;
import homepunk.lesson.series.presenter.TopRatedFragmentPresenter;
import homepunk.lesson.series.presenter.MainFragmentPresenter;
import homepunk.lesson.series.presenter.SearchFragmentPresenter;

@Module
public class PresentersModule {
    @Provides
    @Singleton
    Presenter.MainFragmentPresenter provideMainFragmentPresenter(Model.DataManagerModel model) {
        return new MainFragmentPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.SearchFragmentPresenter provideSearchFragmentPresenter(Model.DataManagerModel model){
        return new SearchFragmentPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.DetailedFragmentPresenter provideDetailedFragmentPresenter(Model.DataManagerModel model){
        return new DetailedFragmentPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.TopRatedFragmentPresenter provideTopRatedFragmentPresenter(Model.DataManagerModel model){
        return new TopRatedFragmentPresenter(model);
    }
}
