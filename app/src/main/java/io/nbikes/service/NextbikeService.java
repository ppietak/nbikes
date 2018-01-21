package io.nbikes.service;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import io.nbikes.data.network.NextbikeApi;
import io.nbikes.data.network.event.NextbikePlacesRequestedEvent;
import io.nbikes.data.network.event.NextbikePlacesUpdatedEvent;
import io.nbikes.data.network.model.City;
import io.nbikes.data.network.model.Country;
import io.nbikes.data.network.model.Place;
import io.nbikes.data.network.response.GetListResponse;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.ui.map.event.MapMovedEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NextbikeService {
    private Bus bus;
    private NextbikeApi api;
    private PlaceRepository repository;

    public NextbikeService(Bus bus, NextbikeApi api, PlaceRepository repository) {
        this.bus = bus;
        this.api = api;
        this.repository = repository;
    }

    @Subscribe
    public void onMapMovedEvent(NextbikePlacesRequestedEvent event) {
        api
                .getList(
                        event.getBounds().southwest.latitude+","+event.getBounds().southwest.longitude,
                        event.getBounds().northeast.latitude+","+event.getBounds().northeast.longitude
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable(new Function<GetListResponse, Iterable<Country>>() {
                    @Override
                    public Iterable<Country> apply(GetListResponse getListResponse) throws Exception {
                        return getListResponse.getCountries();
                    }
                })
                .flatMapIterable(new Function<Country, Iterable<City>>() {
                    @Override
                    public Iterable<City> apply(Country country) throws Exception {
                        return country.getCities();
                    }
                })
                .flatMapIterable(new Function<City, Iterable<Place>>() {
                    @Override
                    public Iterable<Place> apply(City city) throws Exception {
                        return city.getPlaces();
                    }
                })
                .map(new Function<Place, io.nbikes.data.model.Place>() {
                    @Override
                    public io.nbikes.data.model.Place apply(Place place) throws Exception {
                        return new io.nbikes.data.model.Place(
                                place.getId(),
                                place.getName(),
                                place.getCityName(),
                                place.getCountryName(),
                                place.getLatitude(),
                                place.getLongitude(),
                                place.getCityZoom()
                        );
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        bus.post(new NextbikePlacesUpdatedEvent());
                    }
                })
                .toList()
                .subscribe(new Consumer<List<io.nbikes.data.model.Place>>() {
                    @Override
                    public void accept(List<io.nbikes.data.model.Place> places) throws Exception {
                        repository.updateAll(places);
                    }
                })
        ;
    }
}
