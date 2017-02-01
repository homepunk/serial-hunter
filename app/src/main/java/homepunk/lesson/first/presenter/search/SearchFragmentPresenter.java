package homepunk.lesson.first.presenter.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import homepunk.lesson.first.interfaces.Presenter;
import homepunk.lesson.first.presenter.common.RecycleViewPresenter;
import homepunk.lesson.first.view.main.SearchFragment;

public class SearchFragmentPresenter implements Presenter.SearchFragmentPresenter {
    private Context context;
    private SearchFragment view;
    private List<RecyclerView> rvList;
    private List<RecycleViewPresenter> rvPresentersList;
    private String newText;

    public SearchFragmentPresenter() {
        this.rvPresentersList = new ArrayList<>();
        this.rvList = new ArrayList<>();
    }

    @Override
    public void setView(SearchFragment view) {
        this.view = view;
    }

    @Override
    public void setRecycleView(RecyclerView view) {
        this.rvList.add(view);
    }

    @Override
    public void getRecommendationTVSeries() {
//        TVListFetchrModel tvTask = new TVListFetchrModel();
//        List<TVSeries> tvList = new ArrayList<>();
//        TVSeriesAdapter adapter = new TVSeriesAdapter(context, tvList);
//        RecycleViewPresenter rvRecommendPresenter = setUpRecycleViewPresenter(adapter, tvList);
//        rvPresentersList.add(rvRecommendPresenter);
//
//        tvTask.setPresenter(this);
//        tvTask.setTVList(tvList);
//        tvTask.setAdapter(adapter);
//        tvTask.setContext(view.getContext());
//        tvTask.setExecuteRef(Constants.TV_REFENECE + Constants.TV_TOP20 + Constants.LANGUAGE_EN + Constants.API_KEY);
//        tvTask.fetch();
//    }

//    @Override
//    public void onSearchViewClicked() {
//        this.task = new TVListFetchrModel();
//        task.setPresenter(this);
//        task.setContext(context);
//        task.setExecuteRef(Constants.TV_SEARCH + newText + Constants.LANGUAGE_EN + Constants.API_KEY);
////        rvPresenter.updateContent(task);
    }
//
//    @Override
//    public void setContext(Context context) {
//        this.context = context;
//    }

//    private RecycleViewPresenter setUpRecycleViewPresenter(TVSeriesAdapter adapter, List<TVSeries> tvList) {
//        RecycleViewPresenter rvPresenter = new RecycleViewPresenter();
//        rvPresenter.setPresenter(this);
//        rvPresenter.setLayoutManeger(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//        rvPresenter.setContext(view.getContext());
//        rvPresenter.setTVList(tvList);
//        rvPresenter.setAdapter(adapter);
//        rvPresenter.setResources(view.getResources());
//        rvPresenter.setUpRecycleView(rvList.get(0));
//        return rvPresenter;
//    }

    @Override
    public void onSearchViewClicked() {

    }
}
