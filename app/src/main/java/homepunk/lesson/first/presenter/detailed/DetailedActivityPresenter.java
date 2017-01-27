package homepunk.lesson.first.presenter.detailed;

import android.support.v4.app.FragmentTransaction;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.detailed.DetailedFragment;

public class DetailedActivityPresenter implements Presenter.DetailedActivity {
    private int id;
    private homepunk.lesson.first.view.detailed.DetailedActivity view;

    public DetailedActivityPresenter(homepunk.lesson.first.view.detailed.DetailedActivity view) {
        this.view = view;
    }

    @Override
    public void sendDataToFragment() {
        id = view.getFromIntent();
        DetailedFragment fragmentDetailed = (DetailedFragment) view.newInstance(id);
        FragmentTransaction ft = view.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }
}
