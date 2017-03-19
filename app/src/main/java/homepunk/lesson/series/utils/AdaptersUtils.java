package homepunk.lesson.series.utils;

import android.content.Context;
import android.database.MatrixCursor;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.model.Series;
import timber.log.Timber;

public class AdaptersUtils {
    public static void loadImage(Context context, String posterPath, ImageView view){
        if (!TextUtils.isEmpty(posterPath))
            Picasso.with(context)
                    .load(posterPath)
                    .placeholder(R.drawable.placeholder_image)
                    .into(view);

        Timber.i(posterPath);
    }

    public static void loadText(TextView textView, String text){
        if(textView != null)
        textView.setText(text);
    }

    public static MatrixCursor convertToCursor(List<Series> results, String[] columns) {
        MatrixCursor cursor = new MatrixCursor(columns);
        int i = 0;
        for (Series series: results) {
            String[] temp = new String[3];
            i = i + 1;
            temp[0] = Integer.toString(i);
            temp[1] = series.getFullPosterPath(Series.WIDTH_154);
            temp[2] = series.getTitle();

            cursor.addRow(temp);
        }

        return cursor;
    }
}
