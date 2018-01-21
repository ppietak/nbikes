package io.nbikes.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cities")
    @Expose
    private List<City> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        for (City city : this.cities) {
            city.setCountryName(name);
        }

        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
