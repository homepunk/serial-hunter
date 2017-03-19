package homepunk.lesson.series.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.Series;

import static homepunk.lesson.series.utils.AdapterUtils.loadImage;
import static homepunk.lesson.series.utils.AdapterUtils.loadText;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Series> seriesList;

    private Context context;
    private LayoutInflater layoutInflater;
    private View root;

    public SeriesRecyclerAdapter(Context context, List<Series> seriesList) {
        this.seriesList = seriesList;
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
        if (series.getViewType() == Series.GRID_TYPE) {
            GridViewHolder viewHolder = ((GridViewHolder) holder);

            loadText(viewHolder.textView, series.getTitle());
            loadText(viewHolder.textViewUpdate, "20/04");
            loadImage(context, series.getFullPosterPath(Series.WIDTH_500), viewHolder.poster);
        } else {
            BackdropViewHolder viewHolder = ((BackdropViewHolder) holder);

            loadImage(context, series.getFullPosterPath(Series.WIDTH_780), viewHolder.poster);
            loadText(viewHolder.title, series.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
