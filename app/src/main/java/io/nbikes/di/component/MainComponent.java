package io.nbikes.di.component;

import dagger.Component;
import io.nbikes.di.module.MainModule;
import io.nbikes.di.scope.CustomScope;
import io.nbikes.ui.main.MainActivity;

@CustomScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity view);
}
