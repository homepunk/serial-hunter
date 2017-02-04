package homepunk.lesson.first.di;

import android.app.Application;
import android.content.Context;

public class App extends Application{
    private AppComponent component;

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
