package io.nbikes.ui.place.list.event;

import io.nbikes.data.model.Place;

public class PlaceSelectedEvent {
    private Place place;

    public PlaceSelectedEvent(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }
}
