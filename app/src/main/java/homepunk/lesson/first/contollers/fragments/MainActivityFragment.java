package homepunk.lesson.first.contollers.fragments;

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

import butterknife.ButterKnife;
import homepunk.lesson.first.adapters.MoviesListAdapter;
import homepunk.lesson.first.contollers.R;
import homepunk.lesson.first.db.Constants;
import homepunk.lesson.first.models.Film;
import homepunk.lesson.first.networking.MovieFetchrAsync;
import homepunk.lesson.first.networking.MovieNetworkParser;

public class MainActivityFragment extends Fragment {

    private RecyclerView MoviesRV;
    private MoviesListAdapter adapter;
    private List<Film> filmsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_main_movies, container, false);

        filmsList = new ArrayList<>();
        MoviesRV = (RecyclerView) root.findViewById(R.id.movies_rv);
        setUpView(root);

        MovieFetchrAsync task = new MovieFetchrAsync(new MovieFetchrAsync.IResultListener() {

            @Override
            public void onResult(String result) {
                if (TextUtils.isEmpty(result))
                    return;

                try {
                    filmsList.addAll(MovieNetworkParser.getFilmsFromJson(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

//        task.execute("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + Constants.API_KEY);
//        task.execute("https://api.themoviedb.org/3/discover/tv?sort_by=popularity.desc&api_key=" + Constants.API_KEY);
        task.execute("https://api.themoviedb.org/3/tv/top_rated?page=1&language=en-US&api_key=" + Constants.API_KEY);
        return root;
    }

    void setUpView(ViewGroup root){
        ButterKnife.bind(this, root);
        setUpList();
    }

    void setUpList() {
        adapter = new MoviesListAdapter(filmsList, getContext());
        MoviesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        MoviesRV.setAdapter(adapter);
        MoviesRV.setLayoutManager(new GridLayoutManager(getContext(), getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
    }

}

