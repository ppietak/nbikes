package io.nbikes.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {
    @SerializedName("name")
    @Expose
    private String name;

    private String countryName;

    @SerializedName("places")
    @Expose
    private List<Place> places;

    @SerializedName("zoom")
    @Expose
    private float zoom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Place> getPlaces() {
        for (Place place : this.places) {
            place.setCountryName(countryName);
            place.setCityName(name);
            place.setCityZoom(zoom);
        }

        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
}
