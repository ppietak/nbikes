package io.nbikes;

import android.app.Application;

import com.orm.SugarContext;

import io.nbikes.di.component.AppComponent;
import io.nbikes.di.component.DaggerAppComponent;
import io.nbikes.di.module.AppModule;
import io.nbikes.di.module.NetworkModule;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class App extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(this);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://nextbike.net/"))
                .build();
        component.bus().register(component.nextbikeService());
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
