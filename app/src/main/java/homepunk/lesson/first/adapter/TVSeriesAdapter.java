package homepunk.lesson.first.adapter;


import android.content.Context;
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
import homepunk.lesson.first.model.Series;

public class TvSeriesAdapter extends RecyclerView.Adapter<TvSeriesAdapter.GridViewHolder> {
    public static final int GRID_TYPE = 0;
    public static final int LIST_TYPE = 1;

    protected static List<Series> tvList;

    private Series tvSeries;
    private Context context;
    private LayoutInflater layoutInflater;
    private View root;

    public TvSeriesAdapter(Context context, List<Series> tvList) {
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

        loadPoster(holder, tvSeries);
    }


    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_poster) ImageView poster;

        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
