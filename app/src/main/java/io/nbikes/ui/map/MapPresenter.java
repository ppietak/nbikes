package io.nbikes.ui.map;

import android.support.annotation.NonNull;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import io.nbikes.data.model.Place;
import io.nbikes.data.network.event.NextbikePlacesUpdatedEvent;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.ui.core.Presenter;
import io.nbikes.ui.map.event.MapReadyEvent;
import io.nbikes.ui.map.event.MarkerSelectedEvent;
import io.nbikes.ui.map.viewmodel.PlaceViewModel;
import io.nbikes.ui.place.list.event.PlaceSelectedEvent;

public class MapPresenter extends Presenter<MapView> {
    public static final int PLACE_ZOOM = 15;
    private Bus bus;
    private PlaceRepository repository;

    public MapPresenter(MapView view, Bus bus, PlaceRepository repository) {
        super(view);
        this.bus = bus;
        this.repository = repository;
    }

    @Override
    protected void afterBind() {
        super.afterBind();
        bus.register(this);
    }

    @Override
    protected void afterUnbind() {
        super.afterUnbind();
        bus.unregister(this);
    }

    @Subscribe
    public void onPlaceSelected(PlaceSelectedEvent event) {
        showPlace(event.getPlace());
    }

    @Subscribe
    public void onMarkerSelected(MarkerSelectedEvent event) {
        Place place = repository.find((Long) event.getMarker().getTag());
        if (place != null) {
            showPlace(place);
        }
    }

    private void showPlace(Place place) {
        getView().showMessage(
                place.getName() + ", " + place.getCityName()
        );
        getView().centerMap(
                place.getLat(),
                place.getLng(),
                PLACE_ZOOM,
                true
        );
    }

    @Subscribe
    public void onMapReady(MapReadyEvent event) {
        getView().centerMap(51.628180725109345, 18.946163579821587, 5, false);
        List<PlaceViewModel> places = loadPlaceViewModels();
        getView().showMarkers(places);
    }

    @Subscribe
    public void onPlacesUpdated(NextbikePlacesUpdatedEvent event) {
        List<PlaceViewModel> places = loadPlaceViewModels();
        getView().showMarkers(places);
    }

    @NonNull
    private List<PlaceViewModel> loadPlaceViewModels() {
        ArrayList<PlaceViewModel> places = new ArrayList<>();

        for (Place place : repository.findAll()) {
            places.add(new PlaceViewModel(
                    place.getId(),
                    place.getLatLng(),
                    place.getName() + ", " + place.getCityName()
            ));
        }

        return places;
    }
}
