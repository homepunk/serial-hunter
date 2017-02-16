package homepunk.lesson.series.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.Series;

public class SeriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int GRID_TYPE = 0;
    public static final int LIST_TYPE = 1;

    protected static List<Series> tvList;

    private Context context;
    private LayoutInflater layoutInflater;
    private View root;

    public SeriesAdapter(Context context, List<Series> tvList) {
        this.tvList = tvList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Series series = tvList.get(position);
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
        Series series = tvList.get(position);
        if (series.getViewType() == Series.GRID_TYPE) {
            GridViewHolder gridVh = ((GridViewHolder) holder);
            gridVh.textView.setText(series.getTitle());
            gridVh.textViewUpdate.setText("20/04 (Ср)");
            gridVh.favorite.getContext();
            if (!TextUtils.isEmpty(series.getFullPosterPath(Series.WIDTH_500)))
                Picasso.with(context)
                        .load(series.getFullPosterPath(Series.WIDTH_500))
                        .placeholder(R.drawable.placeholder_image)
                        .into(gridVh.poster);
        } else {
            BackdropViewHolder backdropVh = ((BackdropViewHolder) holder);
            if (!TextUtils.isEmpty(series.getFullBackdropPath(Series.WIDTH_780)))
                Picasso.with(context)
                        .load(series.getFullBackdropPath(Series.WIDTH_780))
                        .placeholder(R.drawable.placeholder_image)
                        .into(backdropVh.poster);
            else Picasso.with(context)
                    .load(series.getFullPosterPath(Series.WIDTH_780))
                    .placeholder(R.drawable.placeholder_image)
                    .into(backdropVh.poster);

            backdropVh.title.setText(series.getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return tvList.size();
    }
}
