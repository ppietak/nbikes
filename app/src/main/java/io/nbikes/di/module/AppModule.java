package io.nbikes.di.module;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.nbikes.App;

@Module
public class AppModule {
    private App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Bus providesBus() {
        return new Bus();
    }
}
