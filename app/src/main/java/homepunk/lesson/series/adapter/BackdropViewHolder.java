package homepunk.lesson.series.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;

public class BackdropViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.item_backdrop_poster) ImageView poster;
    @Bind(R.id.backdrop_title) TextView title;

    public BackdropViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
