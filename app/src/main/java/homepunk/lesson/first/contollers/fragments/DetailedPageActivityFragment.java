package homepunk.lesson.first.contollers.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.contollers.activity.DetailedPageActivity;
import homepunk.lesson.first.db.Constants;

public class DetailedPageActivityFragment extends Fragment {

    public DetailedPageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_detailed_page, container, false);
        // Получаю айдишник из DetailedPageActivity
        int value = DetailedPageActivity.getDetailedValue();
        TextView textView = (TextView) root.findViewById(R.id.id_detailed_overview);
        textView.setText("ID = " + value);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Quicksand-Regular.ttf");
        textView.setTypeface(typeFace);

        // KenBurnsView imageView = (KenBurnsView) findViewById(R.id.item_poster);
        ImageView imageView = (ImageView) root.findViewById(R.id.item_poster);
        /*Picasso.with(getContext())
                .load(film.getFullPosterPath(Film.WIDTH_500))
                .into(imageView);
*/
        return root;
    }


    public static DetailedPageActivityFragment newInstance(int id) {
        DetailedPageActivityFragment fragment = new DetailedPageActivityFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(Constants.TV_ID, id);
        fragment.setArguments(arguments);

        return fragment;
    }

    public int getValuesFromBundle() {
        Bundle bundle = getArguments();
        return bundle != null ? bundle.getInt(Constants.TV_ID) : 0;
    }
}
