package io.nbikes.di.module;

import dagger.Module;
import dagger.Provides;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.place.list.PlaceListPresenter;
import io.nbikes.ui.place.list.PlaceListView;

@Module
public class PlaceListModule {
    private PlaceListView view;

    public PlaceListModule(PlaceListView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    PlaceListPresenter providesPlaceListPresenter() {
        return new PlaceListPresenter(view);
    }
}
