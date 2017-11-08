package io.nbikes.di.module;

import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.map.MapPresenter;
import io.nbikes.ui.map.MapView;

@Module
public class MapModule {
    private MapView view;

    public MapModule(MapView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MapPresenter providesMapPresenter(Bus bus) {
        return new MapPresenter(view, bus);
    }
}
