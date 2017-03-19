package homepunk.lesson.series.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.utils.AdapterUtils;

public class SearchResultsAdapter extends android.support.v4.widget.SimpleCursorAdapter {
    private Context context;
    private int layout;

    public SearchResultsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.layout = layout;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SearchViewHolder holder = new SearchViewHolder(view);

        holder.setPoster(cursor.getString(1));
        holder.setTitle(cursor.getString(2));
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    class SearchViewHolder {
        @Bind(R.id.item_search_poster)
        ImageView imageView;
        @Bind(R.id.item_search_title)
        TextView textView;

        public SearchViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setPoster(String path){
            AdapterUtils.loadImage(context, path, imageView);
        }

        public void setTitle(String title) {
            AdapterUtils.loadText(textView, title);
        }
    }
}
