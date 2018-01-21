package io.nbikes.ui.map.event;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MapMovedEvent {
    private LatLng target;
    private LatLngBounds region;
    private float zoom;

    public MapMovedEvent(LatLng target, LatLngBounds region, float zoom) {
        this.target = target;
        this.region = region;
        this.zoom = zoom;
    }

    public LatLng getTarget() {
        return target;
    }

    public LatLngBounds getRegion() {
        return region;
    }

    public float getZoom() {
        return zoom;
    }
}
