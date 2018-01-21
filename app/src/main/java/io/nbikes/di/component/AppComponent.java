package io.nbikes.di.component;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.di.module.AppModule;
import io.nbikes.di.module.DataModule;
import io.nbikes.di.module.NetworkModule;
import io.nbikes.service.NextbikeService;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {
    Bus bus();
    NextbikeService nextbikeService();
    PlaceRepository placeRepository();
}
