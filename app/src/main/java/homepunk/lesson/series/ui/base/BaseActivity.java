package homepunk.lesson.series.ui.base;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.adapter.ListPopupAdapter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.utils.NavigationUtils;
import timber.log.Timber;

import static homepunk.lesson.series.utils.ScreenUtils.convertToPx;
import static homepunk.lesson.series.utils.ScreenUtils.getDisplayContentHeight;
import static homepunk.lesson.series.utils.ScreenUtils.getDisplayContentWidth;
import static homepunk.lesson.series.utils.ScreenUtils.getDrawableFromXml;

public class BaseActivity extends AppCompatActivity {
    private final int[] keyBoardHeight = new int[1];
    private ListView listView;
    protected MaterialSearchView searchView;
    protected ListPopupAdapter listPopupAdapter;
    protected PopupWindow popupWindow;
    protected List<Series> searchList;
    protected View popupAnchor;
    private int minHeight;

    protected void setupPopupWindow() {
        popupAnchor = ButterKnife.findById(this, R.id.popup_anchor);
        popupWindow = new PopupWindow(this);
        setupListView();

        popupWindow.setContentView(listView);
        popupWindow.setFocusable(false);
        popupWindow.setHeight(getPopupHeight());
        popupWindow.setWidth(getPopupWidth());

        Drawable popupRoundedCorners = getDrawableFromXml(getResources(), R.xml.rounded_corners_popup);
        popupWindow.setBackgroundDrawable(popupRoundedCorners);
    }

    private void setupListView() {
        listView = new ListView(this);
        searchList = new ArrayList<>();
        listPopupAdapter = new ListPopupAdapter(this, searchList);

        listView.setAdapter(listPopupAdapter);
//        listView.setDividerHeight(0);
//        listView.setDivider(null);
        listView.setOnItemClickListener((parent, view, position, id) ->
                NavigationUtils.navigateToDetailed(this, searchList.get(position).getId()));
    }

    protected void setupSearchView() {
        searchView = ButterKnife.findById(this, R.id.searchview);
        CoordinatorLayout coordinatorLayout = ButterKnife.findById(this, R.id.main_container);

        AlphaAnimation backgroundAlphaOpen= new AlphaAnimation(1f, 0.2f);
        backgroundAlphaOpen.setDuration(500);
        backgroundAlphaOpen.setStartOffset(0);
        backgroundAlphaOpen.setFillAfter(true);

        AlphaAnimation backgroundAlphaClose = new AlphaAnimation(0.2f, 1.0f);
        backgroundAlphaClose.setDuration(500);
        backgroundAlphaClose.setStartOffset(0);
        backgroundAlphaClose.setFillAfter(true);

        searchView.setScrollBarFadeDuration(5000);
        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                coordinatorLayout.startAnimation(backgroundAlphaOpen);
            }

            @Override
            public void onSearchViewClosed() {
                coordinatorLayout.startAnimation(backgroundAlphaClose);

                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        searchView.setHint(getString(R.string.hint_search));
        searchView.setShouldAnimate(true);
        searchView.setTintColor((Color.rgb(255, 255, 255)));
        searchView.setTintAlpha(20);
    }

    protected void showDropDown() {
        if (!popupWindow.isShowing())
            popupWindow.showAsDropDown(popupAnchor, 0, 0, Gravity.CENTER);

        if (isPopupSizeChanged())
            popupWindow.update(popupAnchor, getPopupWidth(), getPopupHeight());
        else
            popupWindow.update();
    }

    protected int getPopupHeight() {
        minHeight = convertToPx(this, 86);
        int actualHeight = searchList.size() * minHeight;
        int margin = convertToPx(this, 8);
        int maxHeight;

        recalculateKeyboardHeight();
        Timber.i("Keyboard height: " + String.valueOf(keyBoardHeight[0]));

        if (keyBoardHeight[0] < 150)
            maxHeight = minHeight;
        else
            maxHeight = getDisplayContentHeight(this) - keyBoardHeight[0] - margin;

        Timber.i("Content height: " + String.valueOf(maxHeight));

        if (searchList.size() == 0)
            return minHeight;
        else if (actualHeight >= maxHeight)
            return maxHeight;
        else
            return actualHeight;
    }

    protected int getPopupWidth() {
        int margin = 12;
        return getDisplayContentWidth(this) - convertToPx(this, margin * 2);
    }

    private boolean isPopupSizeChanged() {
        int actualHeight = getPopupHeight();

        return actualHeight >= minHeight * 5 || actualHeight >= minHeight;
    }

    private void recalculateKeyboardHeight() {
        View root = getWindow().getDecorView();
        root.getViewTreeObserver().
                addOnGlobalLayoutListener(
                        () -> {
                            Rect r = new Rect();
                            root.getWindowVisibleDisplayFrame(r);

                            int screenHeight = root.getRootView()
                                    .getHeight();
                            int heightDifference = screenHeight
                                    - (r.bottom - r.top);
                            int resourceId = getResources()
                                    .getIdentifier("status_bar_height",
                                            "dimen", "android");
                            if (resourceId > 0) {
                                heightDifference -= getResources()
                                        .getDimensionPixelSize(resourceId);
                            }
                            if (heightDifference > 100) {
                                keyBoardHeight[0] = heightDifference;
                            }
                        });
    }
}
