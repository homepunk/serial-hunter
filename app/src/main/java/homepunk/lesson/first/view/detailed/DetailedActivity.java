package homepunk.lesson.first.view.detailed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.data.database.Constants;


public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        sendIdToFragment(getIdFromIntent());
    }

    private void sendIdToFragment(int id){
        DetailedFragment fragmentDetailed = newInstance(id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment_id, fragmentDetailed);
        ft.commit();
    }

    private int getIdFromIntent() {
        Intent intent = getIntent();
        return (int) intent.getSerializableExtra(Constants.TV_ID);
    }

    private DetailedFragment newInstance(int id) {
        DetailedFragment fragment = new DetailedFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.TV_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }

}
