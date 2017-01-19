package homepunk.lesson.first.presenter.detailed;

import android.support.v4.app.FragmentTransaction;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.detailed.DetailedActivity;
import homepunk.lesson.first.view.detailed.DetailedActivityFragment;

public class DetailedActivityPresenter implements Presenter.DetailedActivityPresenter {
    private int id;
    private DetailedActivity view;

    public DetailedActivityPresenter(DetailedActivity view) {
        this.view = view;
    }

    @Override
    public void sendDataToFragment() {
        id = view.getFromIntent();
        DetailedActivityFragment fragmentDetailed = (DetailedActivityFragment) view.newInstance(id);
        FragmentTransaction ft = view.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }
}
