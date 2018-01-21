package io.nbikes.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.nbikes.data.repository.PlaceRepository;

@Module
public class DataModule {

    @Provides
    @Singleton
    PlaceRepository providesRepository() {
        return new PlaceRepository();
    }
}
