package homepunk.lesson.first.contollers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import homepunk.lesson.first.contollers.R;

public class DetailedPageActivityFragment extends Fragment {

    public DetailedPageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detailed_page, container, false);
        return root;
    }
}
