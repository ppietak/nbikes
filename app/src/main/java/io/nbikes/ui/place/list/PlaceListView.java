package io.nbikes.ui.place.list;

import java.util.List;

import io.nbikes.data.model.Place;
import io.nbikes.ui.core.PresenterCompliantView;

public interface PlaceListView extends PresenterCompliantView {
    void loadPlaceList(List<Place> places);
}
