package homepunk.lesson.first.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    Presenter.MainFragmentPresenter provideMainFragmentPresenter(Context context) {
        return new MainFragmentPresenter(context);
    }

    @Provides
    @Singleton
    Presenter.SearchFragmentPresenter provideSearchFragmentPresenter(Context context){
        return new SearchFragmentPresenter(context);
    }

    @Provides
    @Singleton
    Presenter.DetailedFragmentPresenter provideDetailedFragmentPresenter(Context context){
        return new DetailedFragmentPresenter(context);
    }
}
