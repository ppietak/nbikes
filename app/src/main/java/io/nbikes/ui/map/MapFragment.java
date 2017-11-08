package io.nbikes.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nbikes.R;
import io.nbikes.di.component.DaggerMapComponent;
import io.nbikes.di.module.MapModule;
import io.nbikes.ui.PresenterCompilantFragment;

public class MapFragment extends PresenterCompilantFragment<MapPresenter> implements MapView {
    @Inject
    public Bus bus;

    @Inject
    public MapPresenter presenter;

    @Override
    protected MapPresenter getPresenter() {
        return presenter;
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

    @OnClick(R.id.button)
    public void onButtonClick(View view) {
        getApp().getAppComponent().bus().post("through bus");
        getPresenter().onButtonClicked("explicite");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
