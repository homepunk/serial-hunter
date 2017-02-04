package homepunk.lesson.first.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;
import homepunk.lesson.first.presenter.main.MainActivityPresenter;
import homepunk.lesson.first.presenter.main.MainFragmentPresenter;
import homepunk.lesson.first.presenter.search.SearchFragmentPresenter;

@Module
public class PresentersModule {

    @Provides
    @Singleton
    Presenter.MainActivityPresenter provideMainActivityPresenter(){
        return new MainActivityPresenter();
    }

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
