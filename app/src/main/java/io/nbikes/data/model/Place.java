package io.nbikes.data.model;

import com.google.android.gms.maps.model.LatLng;
import com.orm.SugarRecord;

public class Place extends SugarRecord {
    private String name;
    private String cityName;
    private String countryName;
    private double lat;
    private double lng;
    private float cityZoom;

    public Place() {
    }

    public Place(Long id, String name, String cityName, String countryName, double lat, double lng, float cityZoom) {
        setId(id);
        this.name = name;
        this.cityName = cityName;
        this.countryName = countryName;
        this.lat = lat;
        this.lng = lng;
        this.cityZoom = cityZoom;
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public float getCityZoom() {
        return cityZoom;
    }

    public LatLng getLatLng() {
        return new LatLng(lat, lng);
    }
}
