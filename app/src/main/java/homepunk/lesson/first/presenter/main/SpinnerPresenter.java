package homepunk.lesson.first.presenter.main;

import android.graphics.PorterDuff;
import android.view.Display;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.Presenter;
import homepunk.lesson.first.view.main.MainActivity;


public class SpinnerPresenter implements Presenter.SpinnerPresenter {
    private MainActivity view;
    private Spinner spinner;
    private String[] data = {"Комедии", "Приколючения", "Детективы", "Драмы", "Ужасы"};
    private RelativeLayout.LayoutParams params;
    public SpinnerPresenter(MainActivity view) {
        this.view = view;
        this.spinner = view.getSpinnerView();
    }

    @Override
    public void attachSpinner() {
        Display display = view.getWindowManager().getDefaultDisplay();
        int spinnerWidth = display.getWidth();
        spinner.setDropDownWidth(spinnerWidth);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view, R.layout.spinner_item, data);
        adapter.setDropDownViewResource(R.layout.spinner_dpordown_item);
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
    }

    @Override
    public void setSpinnerArrowColor(int color) {
        spinner.getBackground().setColorFilter(view.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);

    }
}
