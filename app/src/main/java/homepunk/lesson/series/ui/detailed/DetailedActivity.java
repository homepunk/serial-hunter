package homepunk.lesson.series.ui.detailed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.utils.NavigationUtils;


public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intent = getIntent();
        int id = NavigationUtils.getId(intent);
        FragmentManager fragmentManager = getSupportFragmentManager();

        NavigationUtils.sendIdToFragment(id, fragmentManager);
    }

}
