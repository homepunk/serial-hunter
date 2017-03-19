package homepunk.lesson.series.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.Series;

import static homepunk.lesson.series.utils.AdapterUtils.loadImage;

public class ListPopupAdapter extends ArrayAdapter<Series> {
    List<Series> seriesList;
    Context context;

    public ListPopupAdapter(@NonNull Context context, List<Series> seriesList) {
        super(context, R.layout.list_item_search);
        this.seriesList = seriesList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return seriesList.size();
    }

    @Nullable
    @Override
    public Series getItem(int position) {
        return seriesList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
        String firstAirYear = "INFITIY";

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Series series = seriesList.get(position);
        if(!TextUtils.isEmpty(series.getFirstAirDate()))
            firstAirYear = series.getFirstAirDate().substring(0, 4);

        holder.loadPoster(series.getFullPosterPath(Series.WIDTH_154));
        holder.loadTitle(series.getTitle() + " (" + firstAirYear + ")");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_search_poster);
            textView = (TextView) view.findViewById(R.id.item_search_title);
        }

        public void loadPoster(String path){
            loadImage(context, path, imageView);
        }

        public void loadTitle(String title){
            textView.setText(title);
        }
    }
}
