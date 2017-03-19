package homepunk.lesson.series.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    public static void recalculateKeyboardHeight(Activity activity,  final int[] keyBoardHeight) {
        View root = activity.getWindow().getDecorView();
        root.getViewTreeObserver().
                addOnGlobalLayoutListener(
                        () -> {
                            Rect r = new Rect();
                            root.getWindowVisibleDisplayFrame(r);

                            int screenHeight = root.getRootView()
                                    .getHeight();
                            int heightDifference = screenHeight
                                    - (r.bottom - r.top);
                            int resourceId = activity.getResources()
                                    .getIdentifier("status_bar_height",
                                            "dimen", "android");
                            if (resourceId > 0) {
                                heightDifference -= activity.getResources()
                                        .getDimensionPixelSize(resourceId);
                            }
                            if (heightDifference > 100) {
                                keyBoardHeight[0] = heightDifference;
                            }
                        });
    }

}
