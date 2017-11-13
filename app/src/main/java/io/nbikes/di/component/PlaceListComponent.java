package io.nbikes.di.component;

import dagger.Component;
import io.nbikes.di.module.PlaceListModule;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.place.list.PlaceListFragment;

@CustomScope
@Component(modules = {PlaceListModule.class}, dependencies = {AppComponent.class})
public interface PlaceListComponent {
    void inject(PlaceListFragment view);
}
