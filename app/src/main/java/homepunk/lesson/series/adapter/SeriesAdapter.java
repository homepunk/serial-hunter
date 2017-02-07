package homepunk.lesson.series.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.Series;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.GridViewHolder> {
    public static final int GRID_TYPE = 0;
    public static final int LIST_TYPE = 1;

    protected static List<Series> tvList;

    private Series tvSeries;
    private Context context;
    private LayoutInflater layoutInflater;
    private View root;

    public SeriesAdapter(Context context, List<Series> tvList) {
        this.tvList = tvList;
        this.context = context;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            layoutInflater = LayoutInflater.from(parent.getContext());
            root = layoutInflater.inflate(R.layout.list_item_tvseries, parent, false);

        return new GridViewHolder(root);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        tvSeries = tvList.get(position);
        if(tvSeries.getTitle() != null)
            holder.textView.setText(tvSeries.getTitle());

        holder.textViewUpdate.setText("20/04 (Ср)");
        holder.favorite.getContext();
        loadPoster(holder, tvSeries);
    }


    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_poster) ImageView poster;
        @Bind(R.id.id_series_title) TextView textView;
        @Bind(R.id.id_series_last_update) TextView textViewUpdate;
        @Bind(R.id.id_image_view) ImageView favorite;
        private boolean isFavorite;

        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ImageView getFavorite() {
            return favorite;
        }

        public boolean isFavorite() {
            return isFavorite;
        }

        public void setFavorite(boolean favorite) {
            isFavorite = favorite;
        }
    }

    private void loadPoster(GridViewHolder holder, Series tvSeries){
        if (!TextUtils.isEmpty(tvSeries.getFullPosterPath(Series.WIDTH_500)))
            Picasso.with(context)
                    .load(tvSeries.getFullPosterPath(Series.WIDTH_500))
                    .placeholder(R.drawable.placeholder_image)
                    .into(holder.poster);
    }
}
