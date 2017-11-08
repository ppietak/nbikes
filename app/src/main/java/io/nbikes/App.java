package io.nbikes;

import android.app.Application;

import io.nbikes.di.component.AppComponent;
import io.nbikes.di.component.DaggerAppComponent;
import io.nbikes.di.module.AppModule;

public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
