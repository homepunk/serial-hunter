package homepunk.lesson.series;

import android.app.Application;
import android.content.Context;

import homepunk.lesson.series.modules.AppModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("onairseries.realm")
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        Timber.plant(new Timber.DebugTree());
    }

    private AppComponent component;

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.component == null)
            app.component = app.createComponent();

        return app.component;
    }
}
