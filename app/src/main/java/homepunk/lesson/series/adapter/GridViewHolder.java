package homepunk.lesson.series.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;

public class GridViewHolder extends RecyclerView.ViewHolder {
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
