package io.nbikes.ui.map;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import io.nbikes.data.event.PlaceSelectedEvent;
import io.nbikes.ui.core.Presenter;

public class MapPresenter extends Presenter<MapView> {
    private Bus bus;

    public MapPresenter(MapView view, Bus bus) {
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

    @Subscribe
    public void onPlaceSelected(PlaceSelectedEvent event) {
        getView().showMessage(event.getPlace().getName() + " selected");
        getView().centerMap(event.getPlace().getLat(), event.getPlace().getLng(), true);
    }
}
