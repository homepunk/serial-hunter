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
import homepunk.lesson.first.db.Constants;
import homepunk.lesson.first.models.TVSeries;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {
    protected static List<TVSeries> filmsList;
    private TVSeries film;
    private Context context;

    public MoviesListAdapter(List<TVSeries> films, Context context) {
        this.filmsList = films;
        this.context = context;
    }

    @Override
    public MoviesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View root = layoutInflater.inflate(R.layout.list_item_film, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        film = filmsList.get(position);

        if (!TextUtils.isEmpty(film.getFullPosterPath(TVSeries.WIDTH_500)))
            Picasso.with(context)
                    .load(film.getFullPosterPath(TVSeries.WIDTH_500))
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
        private TVSeries film;

        public ViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.item_poster);
            // title = (TextView) itemView.findViewById(R.id.item_title);
            poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Context context = v.getContext();
            Intent intent = new Intent(context, DetailedPageActivity.class);
            film = filmsList.get(getAdapterPosition());

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) v.getContext(), poster, "profile");
            intent.putExtra(Constants.TV_ID, film.id);
            context.startActivity(intent, options.toBundle());

        }
    }
}
