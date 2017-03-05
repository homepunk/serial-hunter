package homepunk.lesson.series.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import homepunk.lesson.series.data.DataManager;
import homepunk.lesson.series.interfaces.Presenter;
import homepunk.lesson.series.model.Series;

import static homepunk.lesson.series.interfaces.View.MainFragmentView;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainViewPresenterTest {
    @Mock
    MainFragmentView view;
    @Mock
    DataManager model;
    @Mock
    List<Series> series;

    Presenter.MainPresenter presenter;
    @Before
    public void setUp() throws Exception {
        presenter = new MainViewPresenter(model);
        presenter.setView(view);
        presenter.getOnAirSeries();
    }

    @Test
    public void setView() throws Exception {

    }

    @Test
    public void test() {
        verify(presenter, never()).unsuscribeFromObservable();
    }
}