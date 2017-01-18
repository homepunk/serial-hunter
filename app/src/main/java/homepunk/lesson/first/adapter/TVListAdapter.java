package homepunk.lesson.first.adapter;


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

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.ui.detailed.DetailedActivity;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVSeries;

public class TVListAdapter extends RecyclerView.Adapter<TVListAdapter.ViewHolder> {
    private TVSeries tvItem;
    private Context context;
    protected static List<TVSeries> tvList;

    public TVListAdapter(Context context, List<TVSeries> tvList) {
        this.tvList = tvList;
        this.context = context;
    }

    @Override
    public TVListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View root = layoutInflater.inflate(R.layout.list_item_film, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        tvItem = tvList.get(position);

        if (!TextUtils.isEmpty(tvItem.getFullPosterPath(TVSeries.WIDTH_500)))
            Picasso.with(context)
                    .load(tvItem.getFullPosterPath(TVSeries.WIDTH_500))
                    .placeholder(R.drawable.placeholder_image)
                    .into(holder.poster);
//             holder.title.setText(tvItem.title);

    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.item_poster) ImageView poster;
        private TVSeries tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            poster.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Context context = v.getContext();
            Intent intent = new Intent(context, DetailedActivity.class);
            tvItem = tvList.get(getAdapterPosition());

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) v.getContext(), poster, "profile");
            intent.putExtra(Constants.TV_ID, tvItem.id);
            context.startActivity(intent, options.toBundle());
        }
    }
}
