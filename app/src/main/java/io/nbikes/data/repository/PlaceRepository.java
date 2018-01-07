package io.nbikes.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.nbikes.data.model.Place;

public class PlaceRepository {
    //fixme implement real one
    public List<Place> findAll() {
        ArrayList<Place> list = new ArrayList<>();
        list.add(new Place(1, "First place", 51, 18));
        list.add(new Place(2, "Second place", 20, 120));
        list.add(new Place(3, "Third place", 1, 15));
        return list;
    }
}
