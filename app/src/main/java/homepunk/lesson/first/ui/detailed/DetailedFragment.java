package homepunk.lesson.first.ui.detailed;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.App;
import homepunk.lesson.first.data.database.Constants;
import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.ui.custom.CustomShadowedView;
import homepunk.lesson.first.utils.CustomViewUtils;
import homepunk.lesson.first.utils.ScreenUtils;

public class DetailedFragment extends Fragment implements homepunk.lesson.first.interfaces.View.DetailedFragmentView {
    @Bind(R.id.custom_shadowed_view_id) CustomShadowedView customShadowedView;
    @Bind(R.id.item_detailed_poster) ImageView ivPoster;
    @Bind(R.id.id_detailed_overview) TextView tvOverview;
    @Bind(R.id.fragment_main_id) RelativeLayout rLayout;
    @Bind(R.id.fab) FloatingActionButton fab;

    @Inject Presenter.DetailedFragmentPresenter detailedFragmentPresenter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed, container, false);
        initUI(root);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        int id = getIdFromBundle();
        detailedFragmentPresenter.setView(this);
        detailedFragmentPresenter.getSeriesDescriptionById(id);
    }

    @Override
    public void onSeriesDescRecieved(TVSeries tvSeries) {
        updateUI(tvSeries);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), "Error: " + error,
                Toast.LENGTH_SHORT).show();
    }

    private void initUI(ViewGroup root){
        ButterKnife.bind(this, root);
        App.getAppComponent(getContext()).plus(this);

        setUpCustomView();
        setUpFAB();
    }

    private void updateUI(TVSeries tvSeries){
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Quicksand-Regular.ttf");
        tvOverview.setTypeface(typeFace);
        tvOverview.setText(tvSeries.overview);

        if (!TextUtils.isEmpty(tvSeries.getFullPosterPath(TVSeries.WIDTH_780)))
        Picasso.with(getContext()).
                load(tvSeries.getFullPosterPath(tvSeries.WIDTH_780))
                .placeholder(R.drawable.placeholder_image)
                .resize(ScreenUtils.getDisplayContentWidth(getContext()),
                        ScreenUtils.getDisplayContentHeight(getContext()))
                .into(ivPoster);
    }

    private void setUpFAB(){
        int left = CustomViewUtils.getLeftMargin(getContext(), 28, customShadowedView.getLineAngleCoef(), customShadowedView.getXOffsetCoef());
        int top = CustomViewUtils.getTopMargin(getContext(), 28, customShadowedView.getLineAngleCoef(), customShadowedView.getXOffsetCoef());

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        params.setMargins(left, top, 0, 0);
        fab.setLayoutParams(params);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Fab works fine", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpCustomView(){
        customShadowedView.setTransparentAlpha(240);
        customShadowedView.setLineAngleCoef(0.7);
        customShadowedView.setxOffsetCoef(0.8);
        customShadowedView.setColor(39, 43, 46);
    }

    private int getIdFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(Constants.TV_ID) : 0;
    }
}

