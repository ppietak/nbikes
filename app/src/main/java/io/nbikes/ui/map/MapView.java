package io.nbikes.ui.map;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.nbikes.ui.core.PresenterCompliantView;
import io.nbikes.ui.map.viewmodel.PlaceViewModel;

public interface MapView extends PresenterCompliantView {
    void showMessage(String message);
    void centerMap(double lat, double lng);
    void centerMap(double lat, double lng, boolean animate);
    void centerMap(double lat, double lng, float zoom, boolean animate);
    void showMarkers(List<PlaceViewModel> markers);
}
