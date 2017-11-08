package io.nbikes.di.component;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import io.nbikes.di.module.AppModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Bus bus();
}
