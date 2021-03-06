package io.nbikes.di.module;

import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.main.MainPresenter;
import io.nbikes.ui.main.MainView;

@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainPresenter providesPresenter(Bus bus) {
        return new MainPresenter(view, bus);
    }
}
