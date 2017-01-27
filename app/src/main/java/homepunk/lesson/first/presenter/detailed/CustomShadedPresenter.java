package homepunk.lesson.first.presenter.detailed;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.widget.RelativeLayout;

import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.custom.CustomShadedView;
import homepunk.lesson.first.view.detailed.DetailedFragment;

public class CustomShadedPresenter implements Presenter.CustomShaded {
    public int width, height, fabSize, marginTopFab, marginLeftFab;
    private DetailedFragment view;
    private CustomShadedView backgroudView;

    public CustomShadedPresenter(DetailedFragment view) {
        this.view = view;
    }

    @Override
    public void addView(RelativeLayout layout) {
        Display display = view.getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = view.getDisplayContentHeight();
        fabSize = (int) convertToPx(28);

        backgroudView = new CustomShadedView(view.getContext(), width, height);

        backgroudView.setColor(39, 43, 46);
        backgroudView.setAlpha(240);
        layout.addView(backgroudView);
    }

    @Override
    public int getMarginTop() {
        return height * 7 / 18 - fabSize;
    }

    @Override
    public int getMarginRight() {
        return backgroudView.getFabLeftMargin(fabSize);
    }

    public float convertToPx(int value) {
        Resources r = view.getActivity().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());
    }

}
