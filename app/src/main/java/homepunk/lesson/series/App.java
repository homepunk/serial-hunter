package homepunk.lesson.series;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import homepunk.lesson.series.modules.AppModule;

public class App extends Application{
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());    }

    protected AppComponent createComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context){
        App app = (App) context.getApplicationContext();
        if(app.component == null)
            app.component = app.createComponent();

        return app.component;
    }
}
