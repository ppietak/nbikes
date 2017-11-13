package io.nbikes.ui.main;

import io.nbikes.ui.core.Presenter;

public class MainPresenter extends Presenter<MainView> {

    public MainPresenter(MainView view) {
        super(view);
    }

    void onPlaceListMenuItemClick() {
        if (getView().isPlaceListOpened()) {
            getView().closePlaceList();
        } else {
            getView().openPlaceList();
        }
    }
}
