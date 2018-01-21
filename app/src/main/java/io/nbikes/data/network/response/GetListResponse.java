package io.nbikes.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.nbikes.data.network.model.Country;

public class GetListResponse {
    @SerializedName("countries")
    @Expose
    private List<Country> countries;

    public GetListResponse(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
