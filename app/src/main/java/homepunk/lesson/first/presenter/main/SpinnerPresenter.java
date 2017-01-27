package homepunk.lesson.first.presenter.main;

import android.graphics.PorterDuff;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.MainActivity;


public class SpinnerPresenter implements Presenter.Spinner {
    private MainActivity view;
    private android.widget.Spinner spinner;
    private boolean visibility;
    private String[] data = {"Комедии", "Приколючения", "Детективы", "Драмы", "Ужасы"};
    private ViewGroup.LayoutParams params;

    public SpinnerPresenter(MainActivity view) {
        this.view = view;
        this.spinner = view.getSpinnerView();
        this.params = spinner.getLayoutParams();
    }


    @Override
    public void attachSpinner() {
        Display display = view.getWindowManager().getDefaultDisplay();
        int spinnerWidth = display.getWidth();
        spinner.setDropDownWidth(spinnerWidth);
        spinner.setLayoutParams(params);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view, R.layout.list_item_spinner, data);
        adapter.setDropDownViewResource(R.layout.list_item_spinner_dpordown);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Title");
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        view.addSpinner(spinner);
        setSpinnerVisibility(true);
    }

    @Override
    public void detachSpinner() {
        ((ViewGroup) spinner.getParent()).removeView(spinner);
    }

    @Override
    public boolean getSpinnerVisibility() {
        return visibility == true ? true : false;
    }

    @Override
    public void setSpinnerVisibility(boolean visibility) {
        if(visibility)
            spinner.setVisibility(View.VISIBLE);
        else spinner.setVisibility(View.INVISIBLE);
        this.visibility = visibility;

    }

    @Override
    public void setSpinnerArrowColor(int color) {
        spinner.getBackground().setColorFilter(view.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);

    }
}
