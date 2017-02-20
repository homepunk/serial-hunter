package homepunk.lesson.series.ui.detailed;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.series.App;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.model.Series;
import homepunk.lesson.series.ui.custom.CustomShadowedView;
import homepunk.lesson.series.utils.ScreenUtils;

import static homepunk.lesson.series.data.Constants.KEY_ID;

public class DetailedFragment extends Fragment implements homepunk.lesson.series.interfaces.View.DetailedFragmentView {
    @Bind(R.id.custom_shadowed_view_id) CustomShadowedView customShadowedView;
    @Bind(R.id.item_detailed_poster) ImageView ivPoster;
    @Bind(R.id.id_detailed_overview) TextView tvOverview;
    @Bind(R.id.fragment_main_id) RelativeLayout rLayout;
    @Bind(R.id.viewpagertab) SmartTabLayout tabLayout;
    @Inject Presenter.DetailedFragmentPresenter detailedFragmentPresenter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed, container, false);
        initUi(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        int id = getIdFromBundle();
        detailedFragmentPresenter.setView(this);
        detailedFragmentPresenter.getDetailedDescription(id);
    }

    @Override
    public void onDetailedDescriptionRecieved(Series series) {
        setUpPoster(series);
        setUpOverview(series);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void initUi(ViewGroup root){
        ButterKnife.bind(this, root);
        App.getAppComponent(getContext()).plus(this);

        setUpCustomView();
//        setUpFAB();
    }

    private int getIdFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(KEY_ID) : 0;
    }

    private void setUpOverview(Series tvSeries){
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Quicksand-Regular.ttf");
        tvOverview.setTypeface(typeFace);
        tvOverview.setText(tvSeries.getOverview());
    }

    private void setUpPoster(Series tvSeries){
        if (!TextUtils.isEmpty(tvSeries.getFullPosterPath(Series.WIDTH_780)))
            Picasso.with(getContext()).
                    load(tvSeries.getFullPosterPath(tvSeries.WIDTH_780))
                    .placeholder(R.drawable.placeholder_image)
                    .resize(ScreenUtils.getDisplayContentWidth(getContext()),
                            ScreenUtils.getDisplayContentHeight(getContext()))
                    .into(ivPoster);
    }

    private void setUpCustomView(){
        customShadowedView.setTransparentAlpha(240);
        customShadowedView.setLineAngleCoef(0.7);
        customShadowedView.setxOffsetCoef(0.8);
        customShadowedView.setColor(39, 43, 46);
    }

    private void setUpTabLayout(){

    }

}

