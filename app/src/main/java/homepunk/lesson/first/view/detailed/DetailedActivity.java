package homepunk.lesson.first.view.detailed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.presenter.detailed.DetailedActivityPresenter;
import homepunk.lesson.first.view.View;


public class DetailedActivity extends AppCompatActivity implements View.DetailedActivityView{

    private DetailedActivityPresenter activityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        activityPresenter = new DetailedActivityPresenter(this);
        activityPresenter.sendDataToFragment();
    }

    @Override
    public int getFromIntent() {
        Intent intent = getIntent();
        return (int) intent.getSerializableExtra(Constants.TV_ID);
    }

    @Override
    public DetailedFragment newInstance(int id) {
        DetailedFragment fragment = new DetailedFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.TV_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }

}
