package io.nbikes.ui.main;

import io.nbikes.ui.core.PresenterCompliantView;

public interface MainView extends PresenterCompliantView {
    void openPlaceList();
    void closePlaceList();
    boolean isPlaceListOpened();
}
