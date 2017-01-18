package homepunk.lesson.first.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import homepunk.lesson.first.adapter.TVListAdapter;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.database.Constants;
import homepunk.lesson.first.model.TVSeries;
import homepunk.lesson.first.networking.TVFetchrAsync;
import homepunk.lesson.first.networking.TVNetworkParser;

public class MainActivityFragment extends Fragment {
    @Bind(R.id.movies_rv)
    RecyclerView TVRecycleView;

    private TVListAdapter TVAdapter;
    private List<TVSeries> TVList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_main_movies, container, false);

        setUpView(root);

        TVFetchrAsync task = new TVFetchrAsync(new TVFetchrAsync.IResultListener() {

            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;

                try {
                    TVList.addAll(TVNetworkParser.getFilmsFromJson(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                TVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }

        });

        task.execute(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
        return root;
    }

    void setUpView(ViewGroup root) {
        ButterKnife.bind(this, root);
        setUpList();
    }

    void setUpList() {
        TVList = new ArrayList<>();

        TVAdapter = new TVListAdapter(TVList, getContext());
        TVRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TVRecycleView.setAdapter(TVAdapter);
        TVRecycleView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }


}

