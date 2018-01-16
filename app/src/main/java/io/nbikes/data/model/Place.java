package io.nbikes.data.model;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    private long id;
    private String name;
    private double lat;
    private double lng;

    public Place(long id, String name, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public LatLng getLatLng() {
        return new LatLng(lat, lng);
    }
}
