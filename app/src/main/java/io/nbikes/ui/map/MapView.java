package io.nbikes.ui.map;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.nbikes.ui.core.PresenterCompliantView;

public interface MapView extends PresenterCompliantView {
    void showMessage(String message);
    void centerMap(double lat, double lng);
    void centerMap(double lat, double lng, boolean animate);
    void showMarkers(List<MarkerOptions> markers);
}
