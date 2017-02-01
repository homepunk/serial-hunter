package homepunk.lesson.first.presenter.detailed;

import android.content.Context;

import homepunk.lesson.first.data.DataRepository;
import homepunk.lesson.first.interfaces.Model;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.interfaces.View;

public class DetailedFragmentPresenter implements Presenter.DetailedFragmentPresenter {

    private View.DetailedFragmentView view;
    private int id;
    private final Model.TVSeriesModel model;

    public DetailedFragmentPresenter(Context context) {
        model = new DataRepository(context);
    }

    @Override
    public void setView(View.DetailedFragmentView view) {
        this.view = view;
    }

    @Override
    public void getTVSeriesDescription() {

    }

}

