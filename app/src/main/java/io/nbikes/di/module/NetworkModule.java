package io.nbikes.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.nbikes.data.network.NextbikeApi;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.service.NextbikeService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//todo rename to nextbike or smth
@Module
public class NetworkModule {
    private String nextbikeApiUrl;

    public NetworkModule(String nextbikeApiUrl) {
        this.nextbikeApiUrl = nextbikeApiUrl;
    }

    @Provides
    @Singleton
    NextbikeService providesNextbikeService(Bus bus, PlaceRepository repository) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        NextbikeApi api = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(nextbikeApiUrl)
                .client(client)
                .build()
                .create(NextbikeApi.class);

        return new NextbikeService(bus, api, repository);
    }
}
