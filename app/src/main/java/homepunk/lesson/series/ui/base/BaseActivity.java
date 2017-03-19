package homepunk.lesson.series.ui.base;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.balysv.materialmenu.MaterialMenuDrawable;

import java.util.ArrayList;
import java.util.List;

import br.com.mauker.materialsearchview.MaterialSearchView;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.adapter.ListPopupAdapter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.ui.custom.MaterialSearchToLine;
import homepunk.lesson.series.utils.DisplayUtils;
import homepunk.lesson.series.utils.KeyboardUtils;
import homepunk.lesson.series.utils.NavigationUtils;
import timber.log.Timber;

import static android.graphics.Color.parseColor;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static homepunk.lesson.series.utils.DisplayUtils.convertToPx;
import static homepunk.lesson.series.utils.DisplayUtils.getDisplayContentHeight;
import static homepunk.lesson.series.utils.DisplayUtils.getDisplayContentWidth;
import static homepunk.lesson.series.utils.DisplayUtils.getDrawableFromXml;

public class BaseActivity extends AppCompatActivity {
    private final int[] keyBoardHeight = new int[1];
    private ListView listView;
    protected MaterialSearchView searchView;
    protected ListPopupAdapter listPopupAdapter;
    protected PopupWindow popupWindow;
    protected List<Series> searchList;
    protected View popupAnchor;
    protected MaterialMenuDrawable materialMenu;
    protected MaterialSearchToLine mSearchView;
    protected EditText mEditText;
    protected CoordinatorLayout coordinatorLayout;
    protected AlphaAnimation openAnim, closeAnim, quickCloseAnim, showClearBtnAnim, showEditTextHintAnim;
    protected Button mClearButton;
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
        listView.setDividerHeight(0);
        listView.setDivider(null);
        listView.setOnItemClickListener((parent, view, position, id) ->
                NavigationUtils.navigateToDetailed(this, searchList.get(position).getId()));
    }

    protected void setupSearchView() {
//        searchView = ButterKnife.findById(this, R.id.searchview);
        searchView.setScrollBarFadeDuration(5000);
        searchView.setSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewOpened() {
                if (openAnim != null)
                    coordinatorLayout.startAnimation(openAnim);
            }

            @Override
            public void onSearchViewClosed() {
                if (closeAnim != null)
                    coordinatorLayout.startAnimation(closeAnim);

                dismissDropDown();
            }
        });

        searchView.setHint(getString(R.string.hint_search));
        searchView.setShouldAnimate(true);
        searchView.setTintColor((Color.rgb(255, 255, 255)));
        searchView.setTintAlpha(20);
    }

    protected void setupAnimatedSearch() {
        mEditText = (EditText) findViewById(R.id.material_edit_text_line);
        mSearchView = ButterKnife.findById(this, R.id.material_search_to_line);
        mClearButton = ButterKnife.findById(this, R.id.material_clear_button);

        mSearchView.setOnClickSearchListener(() -> openAnimatedSearch());

        mSearchView.setOnChangeListener(state -> {
            switch (state) {
                case LINE:
                    mEditText.setVisibility(VISIBLE);
                    mEditText.setFocusable(true);
                    mEditText.setFocusableInTouchMode(true);
                    mEditText.requestFocus();
                    mEditText.startAnimation(showEditTextHintAnim);
                    mClearButton.startAnimation(showClearBtnAnim);

                    break;
                case SEARCH:
                    mEditText.setVisibility(GONE);
                    break;
            }
        });
    }

    protected void openAnimatedSearch() {
        if (coordinatorLayout != null && openAnim != null)
            coordinatorLayout.startAnimation(openAnim);

        mSearchView.transformToLine();
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, true);
        mEditText.setHintTextColor(parseColor("#a4a4a4"));
        KeyboardUtils.showSoftKeyboard(this, mEditText);
    }

    protected void closeAnimatedSearch(AlphaAnimation animation, boolean drawTouch) {
        if (mEditText != null && mEditText.getVisibility() == VISIBLE) {
            if (mEditText.getText().length() > 0) {
                mEditText.getText().clear();
            }

            mClearButton.setBackgroundColor(Color.TRANSPARENT);
            mClearButton.setVisibility(GONE);
            mEditText.setHintTextColor(Color.TRANSPARENT);
            mEditText.setCursorVisible(false);

            mSearchView.transformToSearch();
            materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER, drawTouch);

            if (animation != null && coordinatorLayout != null)
                coordinatorLayout.startAnimation(animation);

            dismissDropDown();
        }
    }

    protected void setupOpenCloseAnimation() {
        coordinatorLayout = ButterKnife.findById(this, R.id.main_container);

        openAnim = new AlphaAnimation(1f, 0.2f);
        openAnim.setDuration(600);
        openAnim.setStartOffset(0);
        openAnim.setFillAfter(true);

        closeAnim = new AlphaAnimation(0.2f, 1.0f);
        closeAnim.setDuration(600);
        closeAnim.setStartOffset(0);
        closeAnim.setFillAfter(true);

        quickCloseAnim = new AlphaAnimation(0.2f, 1.0f);
        quickCloseAnim.setDuration(100);
        quickCloseAnim.setStartOffset(0);
        quickCloseAnim.setFillAfter(true);

        showClearBtnAnim = new AlphaAnimation(0.0f, 1f);
        showClearBtnAnim.setDuration(300);
        showClearBtnAnim.setStartOffset(0);
        showClearBtnAnim.setFillAfter(true);
        showClearBtnAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mClearButton.setBackground(DisplayUtils.getDrawableFromXml(getResources(), R.drawable.ic_close_gray));
                mClearButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        showEditTextHintAnim = new AlphaAnimation(0.0f, 1.0f);
        showEditTextHintAnim.setStartOffset(0);
        showEditTextHintAnim.setDuration(300);
        showEditTextHintAnim.setFillAfter(true);
        showEditTextHintAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mEditText.setHintTextColor(parseColor("#a4a4a4"));
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    protected void showDropDown() {
        if (!popupWindow.isShowing())
            popupWindow.showAsDropDown(popupAnchor, 0, 0, Gravity.CENTER);

        if (isPopupSizeChanged())
            popupWindow.update(popupAnchor, getPopupWidth(), getPopupHeight());
        else
            popupWindow.update();
    }

    protected void dismissDropDown() {
        if (popupWindow != null && popupWindow.isShowing())
            popupWindow.dismiss();

        if (keyBoardHeight[0] > 150)
            KeyboardUtils.hideSoftKeyboard(this);
    }

    protected int getPopupHeight() {
        minHeight = convertToPx(this, 86);
        int actualHeight = searchList.size() * minHeight;
        int margin = convertToPx(this, 8);
        int maxHeight;

        KeyboardUtils.recalculateKeyboardHeight(this, keyBoardHeight);

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
        int margin = 64;
        return getDisplayContentWidth(this) - convertToPx(this, margin * 2);
    }

    private boolean isPopupSizeChanged() {
        int actualHeight = getPopupHeight();

        return actualHeight >= minHeight * 5 || actualHeight >= minHeight;
    }
}
