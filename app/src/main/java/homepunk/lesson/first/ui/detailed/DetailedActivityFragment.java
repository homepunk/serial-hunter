package homepunk.lesson.first.ui.detailed;

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
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.presenter.detailed.CustomShadedPresenter;
import homepunk.lesson.first.presenter.detailed.DetailedFragmentPresenter;
import homepunk.lesson.first.presenter.detailed.FabPresenter;

public class DetailedActivityFragment extends Fragment implements homepunk.lesson.first.view.View.DetailedFragmentView {
    @Bind(R.id.fragment_main_id) RelativeLayout relativeLayout;
    @Bind(R.id.id_detailed_overview) TextView tvDescription;
    @Bind(R.id.item_detailed_poster) ImageView ivPoster;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.fab_1) FloatingActionButton fab1;
    @Bind(R.id.fab_2) FloatingActionButton fab2;
    @Bind(R.id.fab_3) FloatingActionButton fab3;

    private DetailedFragmentPresenter mainPresenter;
    private FabPresenter fabPresenter;
    private CustomShadedPresenter shadedPresenter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed_page, container, false);

        ButterKnife.bind(this, root);

        mainPresenter = new DetailedFragmentPresenter(this);
        mainPresenter.getDetailedFromNetwork();

        shadedPresenter = new CustomShadedPresenter(this);
        shadedPresenter.addView(relativeLayout);

        fabPresenter = new FabPresenter(this);
        fabPresenter.loadFabAnimation();
        fabPresenter.setMainFabClickListener(fab);
        fabPresenter.setFabsClickListeners(fab1, fab2, fab3);
        return root;
    }

    @Override
    public int getDataFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(Constants.TV_ID) : 0;
    }

    @Override
    public void setOverview(String o) {
        setFont("fonts/Quicksand-Regular.ttf");
        this.tvDescription.setText(o);
    }

    @Override
    public void setPosterImage(String path) {
        Picasso.with(getContext()).load(path)
                .resize(shadedPresenter.width, shadedPresenter.height)
                .into(ivPoster);
    }

    @Override
    public int getFabMarginTop() {
        return shadedPresenter.getMarginTop();
    }

    @Override
    public int getFabMarginLeft() {
        return shadedPresenter.getMarginRight();
    }

    @Override
    public RelativeLayout.LayoutParams getLayoutParams() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        return params;
    }

    void setFont(String font) {
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), font);
        tvDescription.setTypeface(typeFace);
    }

    public int getDisplayContentHeight() {
        int statusBarHeight;
        Window win = getActivity().getWindow();
        // Get the height of Status Bar
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        else statusBarHeight = 0;
        // Get the screen size
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        return screenHeight - statusBarHeight;
    }

    public static DetailedActivityFragment newInstance(int id) {
        DetailedActivityFragment fragment = new DetailedActivityFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.TV_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }

}

