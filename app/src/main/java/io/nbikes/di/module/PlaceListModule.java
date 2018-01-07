package io.nbikes.di.module;

import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.place.list.PlaceListPresenter;
import io.nbikes.ui.place.list.PlaceListView;
import io.nbikes.ui.place.list.view.PlaceListAdapter;

@Module
public class PlaceListModule {
    private PlaceListView view;

    public PlaceListModule(PlaceListView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    PlaceListPresenter providesPresenter(PlaceRepository repository) {
        return new PlaceListPresenter(view, repository);
    }

    @Provides
    @CustomScope
    PlaceRepository providesRepository() {
        return new PlaceRepository();
    }

    @Provides
    @CustomScope
    PlaceListAdapter providesListAdapter(Bus bus) {
        return new PlaceListAdapter(bus);
    }
}
