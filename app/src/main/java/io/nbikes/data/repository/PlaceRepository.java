package io.nbikes.data.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.nbikes.data.model.Place;

public class PlaceRepository {
    public void persist(Place place) {
        Place.save(place);
    }

    public void updateAll(List<Place> places) {
        Place.deleteAll(Place.class);
        for (Place place : places) {
            Place.save(place);
        }
    }

    public List<Place> findAll() {
        Log.i("repository", "findAll " + Place.listAll(Place.class).size());
        return Place.listAll(Place.class);
    }

    public Place find(Long id) {
        return Place.findById(Place.class, id);
    }
}
