package homepunk.lesson.first.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.contollers.activity.DetailedPageActivity;
import homepunk.lesson.first.models.Film;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {
    protected static List<Film> filmsList;
    private static Film film;
    private Context context;

    public MoviesListAdapter(List<Film> films, Context context) {
        this.filmsList = films;
        this.context = context;
    }

    @Override
    public MoviesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); // ???
        View root = layoutInflater.inflate(R.layout.list_item_film, parent, false);

       /* TextView myTextView = (TextView) root.findViewById(R.id.item_title);

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Regular.ttf");
        myTextView.setTypeface(typeFace);
       */
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        film = filmsList.get(position);

        if (!TextUtils.isEmpty(film.getFullPosterPath(Film.WIDTH_500)))
            Picasso.with(context)
                    .load(film.getFullPosterPath(Film.WIDTH_500))
                    .placeholder(R.drawable.placeholder_image)
                    .into(holder.poster);
//             holder.title.setText(film.title);

    }

    @Override
    public int getItemCount() {
        return filmsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView poster;
       // public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.item_poster);
           // title = (TextView) itemView.findViewById(R.id.item_title);
            poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailedPageActivity.class);
            Film film = filmsList.get(getLayoutPosition());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) v.getContext(), poster, "profile");
            intent.putExtra("desc", film);
            v.getContext().startActivity(intent, options.toBundle());

        }
    }
}
