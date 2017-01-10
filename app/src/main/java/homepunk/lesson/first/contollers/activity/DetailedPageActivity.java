package homepunk.lesson.first.contollers.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.models.Film;


public class DetailedPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Film film = (Film) intent.getSerializableExtra("desc");
        TextView textView = (TextView) findViewById(R.id.id_detailed_overview);
        // KenBurnsView imageView = (KenBurnsView) findViewById(R.id.item_poster);
        ImageView imageView = (ImageView) findViewById(R.id.item_poster);
        Picasso.with(getBaseContext())
                .load(film.getFullPosterPath(Film.WIDTH_500))
                .into(imageView);
        textView.setText(film.overview);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.ttf");
        textView.setTypeface(typeFace);

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

}
