package homepunk.lesson.first.ui.detailed;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;
import homepunk.lesson.first.ui.CustomShadedView;

public class DetailedActivityFragment extends Fragment {
    public TVSeries tvShow;
    private int tvId;
    ImageView imagePoster;
    FloatingActionButton fab;
    TextView textDescription;
    RelativeLayout relativeLayout;

    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
    private boolean FAB_Status = false;
    int width, height, fabSize, marginTopFab, marginLeftFab;
    public Listener listener;
    public DetailedActivityFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed_page, container, false);
        relativeLayout = (RelativeLayout) root.findViewById(R.id.fragment_main_id);
        textDescription = (TextView) root.findViewById(R.id.id_detailed_overview);
        imagePoster = (ImageView) root.findViewById(R.id.item_detailed_poster);
        fab = (FloatingActionButton) root.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) root.findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) root.findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) root.findViewById(R.id.fab_3);

        tvId = getValuesFromBundle();

        TVFetchrAsync task = (TVFetchrAsync) new TVFetchrAsync(new TVFetchrAsync.IResultListener() {

            @Override
            public void onResult(String result) {
                try {
//                    listener = new Listener();
                    tvShow = TVNetworkParser.getDetailedByJsonId(result);
                    textDescription.setText(tvShow.overview);
//                    listener.update(tvResult);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
//            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
             }
        }).execute(Constants.TV_REFENECE + tvId + Constants.LANGUAGE_EN  + Constants.API_KEY);

//        if (tvShow != null) {
//            textDescription.setText(tvShow.overview);
//        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = getDisplayContentHeight();
        fabSize = (int) convertToPx(28);

        CustomShadedView backgroudView = new CustomShadedView(getContext(), width, height);
        backgroudView.setColor(39, 43, 46);
        backgroudView.setAlpha(240);
        relativeLayout.addView(backgroudView);
        marginTopFab = height * 7 / 18 - fabSize;
        marginLeftFab = backgroudView.getFabLeftMargin(fabSize);
        params.setMargins(marginLeftFab, marginTopFab, 0, 0);
        fab.setLayoutParams(params);
        if (tvShow != null){
            Picasso.with(getContext()).load(tvShow.getFullPosterPath(TVSeries.WIDTH_780))
                    .resize(width, height)
                    .into(imagePoster);
        }
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Quicksand-Regular.ttf");
        View.OnClickListener clickListener;
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };

        fab.setOnClickListener(clickListener);
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);

        show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab3_hide);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "Floating Action Button 1", Toast.LENGTH_SHORT).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "Floating Action Button 2", Toast.LENGTH_SHORT).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "Floating Action Button 3", Toast.LENGTH_SHORT).show();
            }
        });


        textDescription.setTypeface(typeFace);
        return root;
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

    public float convertToPx(int value) {
        Resources r = getActivity().getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, r.getDisplayMetrics());

    }

    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin = (int) (fab1.getWidth() * 1.8);
        layoutParams.topMargin = (int) (fab2.getHeight() * 1);

        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin = (int) (fab2.getWidth() * 2.2);
        layoutParams2.topMargin = (int) (fab2.getHeight() * 1.4);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin = (int) (fab3.getWidth() * 1.4);
        layoutParams3.topMargin = (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }

    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }

    public static DetailedActivityFragment newInstance(int id) {
        DetailedActivityFragment fragment = new DetailedActivityFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.TV_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }

    public int getValuesFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(Constants.TV_ID) : 0;
    }

    public class Listener{
        public void update(TVSeries tvSeries) {
            tvShow = tvSeries;
        }
    }
}

