package io.nbikes.ui.place.list;

import io.nbikes.data.repository.PlaceRepository;
import io.nbikes.ui.core.Presenter;

public class PlaceListPresenter extends Presenter<PlaceListView> {
    private PlaceRepository repository;

    public PlaceListPresenter(PlaceListView view, PlaceRepository repository) {
        super(view);
        this.repository = repository;
    }

    @Override
    protected void afterBind() {
        super.afterBind();
        getView().loadPlaceList(repository.findAll());
    }
}
