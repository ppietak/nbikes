package io.nbikes.ui.map.event;

import com.google.android.gms.maps.model.Marker;

public class MarkerSelectedEvent {
    private Marker marker;

    public MarkerSelectedEvent(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }
}
