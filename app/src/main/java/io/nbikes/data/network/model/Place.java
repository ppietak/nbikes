package io.nbikes.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("uid")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    private String cityName;

    private String countryName;

    @SerializedName("lat")
    @Expose
    private double latitude;

    @SerializedName("lng")
    @Expose
    private double longitude;

    private float cityZoom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getCityZoom() {
        return cityZoom;
    }

    public void setCityZoom(float cityZoom) {
        this.cityZoom = cityZoom;
    }
}
