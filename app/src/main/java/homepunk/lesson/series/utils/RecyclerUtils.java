package homepunk.lesson.series.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import homepunk.lesson.first.contollers.R;

public class RecyclerUtils {
    public static void loadImage(Context context, String posterPath, ImageView view){
        if (!TextUtils.isEmpty(posterPath))
            Picasso.with(context)
                    .load(posterPath)
                    .placeholder(R.drawable.placeholder_image)
                    .into(view);
    }

    public static void loadText(TextView textView, String text){
        if(textView != null)
        textView.setText(text);
    }
}
