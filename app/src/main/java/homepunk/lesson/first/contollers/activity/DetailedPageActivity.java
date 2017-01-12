package homepunk.lesson.first.contollers.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import homepunk.lesson.first.contollers.R;


public class DetailedPageActivity extends AppCompatActivity {
    public static Intent intent;
//  Переменная, в которую сохраняю полученый айди из адаптера
    private static int tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        tv_id = (int) intent.getSerializableExtra("desc");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static int getDetailedValue(){
        return tv_id;
    }

}
