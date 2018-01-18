package io.nbikes.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import io.nbikes.R;
import io.nbikes.di.component.DaggerMainComponent;
import io.nbikes.di.module.MainModule;
import io.nbikes.ui.core.PresenterCompliantActivity;
import io.nbikes.ui.map.MapFragment;
import io.nbikes.ui.place.list.PlaceListFragment;

public class MainActivity extends PresenterCompliantActivity implements MainView {
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DaggerMainComponent.builder()
                .appComponent(getApp().getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        registerPresenter(presenter);

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new MapFragment())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_map_places:
                presenter.onPlaceListMenuItemClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void openPlaceList() {
        if (getSupportFragmentManager().findFragmentById(R.id.over_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_bottom)
                    .replace(R.id.over_container, new PlaceListFragment())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void closePlaceList() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.over_container);
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_top)
                    .remove(fragment)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public boolean isPlaceListOpened() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.over_container);
        return fragment != null && fragment instanceof PlaceListFragment;
    }
}
