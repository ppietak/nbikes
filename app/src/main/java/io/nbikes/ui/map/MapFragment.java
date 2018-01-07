package io.nbikes.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.nbikes.R;
import io.nbikes.di.component.DaggerMapComponent;
import io.nbikes.di.module.MapModule;
import io.nbikes.ui.core.PresenterCompilantFragment;

public class MapFragment extends PresenterCompilantFragment<MapPresenter> implements MapView, OnMapReadyCallback {
    @Inject
    public Bus bus;

    @Inject
    public MapPresenter presenter;

    private GoogleMap map;

    @Override
    protected MapPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = new SupportMapFragment();
        mapFragment.getMapAsync(this);

        MapsInitializer.initialize(getActivity());

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mapFragment)
                .commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMapComponent.builder()
                .appComponent(getApp().getAppComponent())
                .mapModule(new MapModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void centerMap(double lat, double lng) {
        centerMap(lat, lng, false);
    }

    @Override
    public void centerMap(double lat, double lng, boolean animate) {
        if (map == null) return;

        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);

        if (animate) {
            map.animateCamera(cameraUpdate);
        } else {
            map.moveCamera(cameraUpdate);
        }
    }
}
