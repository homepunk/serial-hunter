package homepunk.lesson.series.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;

import homepunk.lesson.series.model.Series;

public class SearchAdapter extends ArrayAdapter<Series> {
    AutoCompleteTextView textView;

    public SearchAdapter(Context context, int resource, Series[] objects) {
        super(context, resource, objects);
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
