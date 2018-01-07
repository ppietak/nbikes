package io.nbikes.ui.main;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import io.nbikes.data.event.PlaceSelectedEvent;
import io.nbikes.ui.core.Presenter;

public class MainPresenter extends Presenter<MainView> {
    private Bus bus;

    public MainPresenter(MainView view, Bus bus) {
        super(view);
        this.bus = bus;
    }

    @Override
    protected void afterBind() {
        super.afterBind();
        bus.register(this);
    }

    @Override
    protected void afterUnbind() {
        super.afterUnbind();
        bus.unregister(this);
    }

    void onPlaceListMenuItemClick() {
        if (getView().isPlaceListOpened()) {
            getView().closePlaceList();
        } else {
            getView().openPlaceList();
        }
    }

    @Override
    public boolean onBackPressed() {
        if (getView().isPlaceListOpened()) {
            getView().closePlaceList();
            return true;
        } else {
            return false;
        }
    }

    @Subscribe
    public void onPlaceSelected(PlaceSelectedEvent event) {
        getView().closePlaceList();
    }
}
