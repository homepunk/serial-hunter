package homepunk.lesson.first.view.detailed;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.presenter.detailed.ShadowViewPresenter;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;
import homepunk.lesson.first.presenter.detailed.FloatingButtonsPresenter;

public class DetailedFragment extends Fragment implements homepunk.lesson.first.interfaces.View.DetailedFragmentView {
    @Bind(R.id.fragment_main_id) RelativeLayout rLayout;
    @Bind(R.id.id_detailed_overview) TextView tvDescription;
    @Bind(R.id.item_detailed_poster) ImageView ivPoster;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.fab_1) FloatingActionButton fab1;
    @Bind(R.id.fab_2) FloatingActionButton fab2;
    @Bind(R.id.fab_3) FloatingActionButton fab3;

    private DetailedFragmentPresenter detailesModule;
    private FloatingButtonsPresenter fabModule;
    private ShadowViewPresenter shadedViewModule;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed, container, false);

        ButterKnife.bind(this, root);

        detailesModule = new DetailedFragmentPresenter(this);
        detailesModule.attachAllViews();

        shadedViewModule = new ShadowViewPresenter(this);
        shadedViewModule.addView(rLayout);

        fabModule = new FloatingButtonsPresenter(this);
        fabModule.setMainFabClickListener(fab);
        fabModule.setFabsClickListeners(fab1, fab2, fab3);
        fabModule.loadFabAnimation();

        return root;
    }

    @Override
    public int getFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(Constants.TV_ID) : 0;
    }

    @Override
    public void setOverview(String o) {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Quicksand-Regular.ttf");
        tvDescription.setTypeface(typeFace);
        this.tvDescription.setText(o);
    }

    @Override
    public void setPosterImage(String path) {
        Picasso.with(getContext()).load(path)
                .resize(shadedViewModule.width, shadedViewModule.height)
                .into(ivPoster);
    }

    @Override
    public int getFabMarginTop() {
        return shadedViewModule.getMarginTop();
    }

    @Override
    public int getFabMarginLeft() {
        return shadedViewModule.getMarginRight();
    }

    @Override
    public int getDisplayContentHeight() {
        int statusBarHeight;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        else statusBarHeight = 0;

        // Current screen size
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        return screenHeight - statusBarHeight;
    }

    @Override
    public RelativeLayout.LayoutParams getLayoutParams() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        return params;
    }

}

