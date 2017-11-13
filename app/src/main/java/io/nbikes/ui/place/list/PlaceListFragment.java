package io.nbikes.ui.place.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.nbikes.R;
import io.nbikes.di.component.DaggerPlaceListComponent;
import io.nbikes.di.module.PlaceListModule;
import io.nbikes.ui.core.PresenterCompilantFragment;

public class PlaceListFragment extends PresenterCompilantFragment<PlaceListPresenter> implements PlaceListView {
    @Inject
    public Bus bus;

    @Inject
    public PlaceListPresenter presenter;

    @Override
    protected PlaceListPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerPlaceListComponent.builder()
                .appComponent(getApp().getAppComponent())
                .placeListModule(new PlaceListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placelist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
