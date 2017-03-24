package homepunk.lesson.series.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.HdrezkaSeries;
import homepunk.lesson.series.model.Series;

import static homepunk.lesson.series.utils.AdapterUtils.loadImage;
import static homepunk.lesson.series.utils.AdapterUtils.loadText;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Series> seriesList;
    protected List<HdrezkaSeries> updateInfo;

    private Context context;
    private LayoutInflater layoutInflater;
    private View root;

    public SeriesRecyclerAdapter(Context context, List<Series> seriesList) {
        this.seriesList = seriesList;
        this.context = context;
    }

    public SeriesRecyclerAdapter(Context context, List<Series> seriesList, List<HdrezkaSeries> updateInfo) {
        this.seriesList = seriesList;
        this.updateInfo = updateInfo;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Series series = seriesList.get(position);
        if (series.getViewType() == Series.GRID_TYPE) return Series.GRID_TYPE;
        if (series.getViewType() == Series.BACKDROP_TYPE) return Series.BACKDROP_TYPE;
        else return Series.NULL_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder vh = null;

        switch (viewType) {
            case Series.GRID_TYPE:
                root = layoutInflater.inflate(R.layout.list_item_grid_poster, parent, false);
                vh = new GridViewHolder(root);
                break;
            case Series.BACKDROP_TYPE:
                root = layoutInflater.inflate(R.layout.list_item_backdrop_poster, parent, false);
                vh = new BackdropViewHolder(root);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Series series = seriesList.get(position);
        HdrezkaSeries seriesUpdate = null;
        if (updateInfo != null)
            seriesUpdate = updateInfo.get(position);

        if (series.getViewType() == Series.GRID_TYPE) {
            GridViewHolder viewHolder = ((GridViewHolder) holder);

            loadText(viewHolder.textView, series.getTitle());
            loadText(viewHolder.textViewUpdate, "20/04");
            loadImage(context, series.getFullPosterPath(Series.WIDTH_500), viewHolder.poster);
        } else {
            BackdropViewHolder viewHolder = ((BackdropViewHolder) holder);

            loadImage(context, series.getFullPosterPath(Series.WIDTH_342), viewHolder.poster);
            if (seriesUpdate != null) {
                loadText(viewHolder.title, seriesUpdate.getMainTitle());
                loadText(viewHolder.info, seriesUpdate.getSeason() + " " + seriesUpdate.getSeries());
                loadText(viewHolder.recordStudio, " (" + seriesUpdate.getRecordStudio() + ")");
            } else
                loadText(viewHolder.title, series.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    class BackdropViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_backdrop_poster)
        ImageView poster;
        @Bind(R.id.backdrop_title)
        TextView title;
        @Bind(R.id.backdrop_info)
        TextView info;
        @Bind(R.id.backdrop_info_record_studio)
        TextView recordStudio;

        public BackdropViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_full_poster)
        ImageView poster;
        @Bind(R.id.id_series_title)
        TextView textView;
        @Bind(R.id.id_series_last_update)
        TextView textViewUpdate;
        @Bind(R.id.id_favorite)
        ImageView favorite;
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
}

