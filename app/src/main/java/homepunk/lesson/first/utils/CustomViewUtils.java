package homepunk.lesson.first.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class CustomViewUtils {
    public static int getTopMargin(Context context, int fabWidth, double lineAngleKoef, double xOffsetCoef) {
        return (int) Math.round((ScreenUtils.getDisplayContentHeight(context) * lineAngleKoef - convertToPx(context, fabWidth)) * xOffsetCoef);
    }

    public static int getLeftMargin(Context context, int fabWidth, double lineAngleKoef, double xOffsetCoef){
        float leftX = 0, rightX = ScreenUtils.getDisplayContentWidth(context);
        float height  = ScreenUtils.getDisplayContentHeight(context);
        float bottomEndX = height;
        float lineStartY = (float) (height * lineAngleKoef);
        float lineEndY = (float) (height * (1 - lineAngleKoef));

        float a1 = lineStartY - lineEndY;
        float b1 = rightX - leftX;
        float c1 = leftX * lineEndY - rightX * lineStartY;
        float a2 = 0;
        float b2 = leftX - rightX;
        float c2 = (rightX * height - leftX * height) * 7 / 18;
        return (int) (((c2 * b1 - c1 * b2) / (a1 * b2 - a2 * b1) - convertToPx(context, fabWidth)) * xOffsetCoef);

    }

    public static float convertToPx(Context context, int value) {
        Resources r = context.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
    }
}
