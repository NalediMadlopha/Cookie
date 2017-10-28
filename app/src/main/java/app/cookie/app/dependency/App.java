package app.cookie.app.dependency;

import android.app.Application;


public class App extends Application {

    protected static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = getAppComponent();
    }

    public static AppComponent appComponent() {
        return appComponent;
    }

    private static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }
}
