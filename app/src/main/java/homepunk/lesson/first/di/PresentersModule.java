package homepunk.lesson.first.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.presenter.DetailedFragmentPresenter;
import homepunk.lesson.first.presenter.MainFragmentPresenter;
import homepunk.lesson.first.presenter.SearchFragmentPresenter;

@Module
public class PresentersModule {
    @Provides
    @Singleton
    Presenter.MainFragmentPresenter provideMainFragmentPresenter(Model.TVSeriesModel model) {
        return new MainFragmentPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.SearchFragmentPresenter provideSearchFragmentPresenter(Model.TVSeriesModel model){
        return new SearchFragmentPresenter(model);
    }

    @Provides
    @Singleton
    Presenter.DetailedFragmentPresenter provideDetailedFragmentPresenter(Model.TVSeriesModel model){
        return new DetailedFragmentPresenter(model);
    }
}
