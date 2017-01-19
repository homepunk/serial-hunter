package homepunk.lesson.first.ui.detailed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.detailed.DetailedActivityPresenter;


public class DetailedActivity extends AppCompatActivity {

    private DetailedActivityPresenter activityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        activityPresenter = new DetailedActivityPresenter(this);
        activityPresenter.putDataFromIntentToBundle();
        activityPresenter.sendDataToFragment();

    }
}
