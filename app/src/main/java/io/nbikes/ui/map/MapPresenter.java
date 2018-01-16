package io.nbikes.ui.map;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import io.nbikes.data.event.PlaceSelectedEvent;
import io.nbikes.data.model.Place;
import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.ui.core.Presenter;
import io.nbikes.ui.map.event.MapReadyEvent;

public class MapPresenter extends Presenter<MapView> {
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
        getView().showMessage(event.getPlace().getName() + " selected");
        getView().centerMap(event.getPlace().getLat(), event.getPlace().getLng(), true);
    }

    @Subscribe
    public void onMapReady(MapReadyEvent event) {
        ArrayList<MarkerOptions> markers = new ArrayList<>();

        for (Place place : repository.findAll()) {
            MarkerOptions options = new MarkerOptions()
                    .position(place.getLatLng())
                    .title(place.getName());
            markers.add(options);
        }

        getView().showMarkers(markers);
    }
}
