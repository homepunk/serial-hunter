package homepunk.lesson.series.interfaces;

public interface Presenter {

    interface MainPresenter {
        void setView(View.MainFragmentView view);

        void getOnAirSeries();

        void onSeriesSelected(int id);

        void unsuscribeFromObservable();
    }

    interface SearchPresenter {
        void setView(View.SearchFragmentView view);

        void getSearchResults(String searchString);

        void onSearchViewClicked();

        void unsuscribeFromObservable();
    }

    interface DetailedPresenter {
        void setView(View.DetailedFragmentView view);

        void getDetailedDescription(int id);

        void unsuscribeFromObservable();
    }

    interface PopularPresenter {
        void setView(View.PopularFragmentView view);

        void getPopularSeries();

        void unsuscribeFromObservable();
    }
}
