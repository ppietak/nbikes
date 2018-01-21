package io.nbikes.data.network.event;

import com.google.android.gms.maps.model.LatLngBounds;

public class NextbikePlacesRequestedEvent {
    private LatLngBounds bounds;

    public NextbikePlacesRequestedEvent(LatLngBounds bounds) {
        this.bounds = bounds;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }
}
