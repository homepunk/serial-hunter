package homepunk.lesson.first.presenter.detailed;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.ui.detailed.DetailedActivity;
import homepunk.lesson.first.ui.detailed.DetailedActivityFragment;

public class DetailedActivityPresenter implements Presenter.DetailedActivityPresenter {
    private DetailedActivity view;
    private int id;

    public DetailedActivityPresenter(DetailedActivity view) {
        this.view = view;
    }

    @Override
    public int getDataFromIntent() {
        Intent intent = view.getIntent();
        return (int) intent.getSerializableExtra(Constants.TV_ID);
    }

    @Override
    public void putDataFromIntentToBundle() {
        id = getDataFromIntent();
    }

    @Override
    public void sendDataToFragment(int id) {
        DetailedActivityFragment fragmentDetailed = DetailedActivityFragment.newInstance(id);
        FragmentTransaction ft = view.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }

    @Override
    public void sendDataToFragment() {
        id = getDataFromIntent();
        DetailedActivityFragment fragmentDetailed = DetailedActivityFragment.newInstance(id);
        FragmentTransaction ft = view.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }
}
