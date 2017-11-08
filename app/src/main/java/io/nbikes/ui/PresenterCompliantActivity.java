package io.nbikes.ui;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


abstract public class PresenterCompliantActivity extends AppCompatActivity implements PresenterCompliantView {
    private List<Presenter> presenters = new ArrayList<>();

    @Override
    public void registerPresenter(Presenter presenter) {
        this.presenters.add(presenter);
    }

    @Override
    public void onBackPressed() {
        for (Presenter presenter : presenters) {
            if (presenter.onBackPressed()) {
                return;
            }
        }

        super.onBackPressed();
    }

    public void forceDefaultBackPressed() {
        super.onBackPressed();
    }
}
