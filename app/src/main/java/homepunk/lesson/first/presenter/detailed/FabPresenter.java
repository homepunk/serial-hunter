package homepunk.lesson.first.presenter.detailed;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.ui.detailed.DetailedActivityFragment;

public class FabPresenter implements Presenter.FabPresenter {
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
    private boolean FAB_Status = false;
    private RelativeLayout.LayoutParams params;
    private DetailedActivityFragment view;
    private FloatingActionButton fab1, fab2, fab3;


    public FabPresenter(DetailedActivityFragment view) {
        this.view = view;
    }

    @Override
    public void setMainFabClickListener(FloatingActionButton fab) {
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
        setFabMargins(fab);
    }

    @Override
    public void setFabsClickListeners(FloatingActionButton fab1, FloatingActionButton fab2, FloatingActionButton fab3) {
        this.fab1 = fab1;
        this.fab2 = fab2;
        this.fab3 = fab3;
        View.OnClickListener clickListener;
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };
        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
    }

    @Override
    public void loadFabAnimation() {
        show_fab_1 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(view.getActivity().getApplication(), R.anim.fab3_hide);
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

    private void setFabMargins(FloatingActionButton fab) {
        params = view.getLayoutParams();
        int marginTop = view.getFabMarginTop();
        int marginLeft = view.getFabMarginLeft();
        params.setMargins(marginLeft, marginTop, 0, 0);
        fab.setLayoutParams(params);
    }

}
