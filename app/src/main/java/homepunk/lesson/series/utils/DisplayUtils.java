package homepunk.lesson.series.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class DisplayUtils {

    public static int getDisplayContentHeight(Context context) {
        int statusBarHeight;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        else statusBarHeight = 0;

        // Current screen size
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;

        return screenHeight - statusBarHeight;
    }

    public static int getDisplayContentWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        return display.getWidth();
    }

    public static int dpToPixels(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Drawable getDrawableFromXml(Resources resources, int id) {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromXml(resources, resources.getXml(id));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

}
