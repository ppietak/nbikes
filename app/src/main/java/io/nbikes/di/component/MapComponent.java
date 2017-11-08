package io.nbikes.di.component;

import dagger.Component;
import io.nbikes.di.module.MapModule;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.map.MapFragment;

@CustomScope
@Component(modules = {MapModule.class}, dependencies = {AppComponent.class})
public interface MapComponent {
    void inject(MapFragment view);
}
