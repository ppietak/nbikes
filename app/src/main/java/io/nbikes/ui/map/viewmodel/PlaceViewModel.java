package io.nbikes.ui.map.viewmodel;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class PlaceViewModel implements ClusterItem {
    private Long id;
    private LatLng position;
    private String title;

    public PlaceViewModel(Long id, LatLng position, String title) {
        this.id = id;
        this.position = position;
        this.title = title;
    }

    @Override
    public String getSnippet() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public LatLng getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }
}
