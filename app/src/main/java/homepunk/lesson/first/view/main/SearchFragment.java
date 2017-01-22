package homepunk.lesson.first.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.presenter.main.SearchPresenter;
import homepunk.lesson.first.view.View.SearchFragmentView;

public class SearchFragment extends Fragment implements SearchFragmentView{
    @Bind(R.id.search_rv) RecyclerView rvSearch;

    private ViewGroup root;
    private SearchPresenter searchModule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public RecyclerView getRecycleView() {
        return this.rvSearch;
    }
}
