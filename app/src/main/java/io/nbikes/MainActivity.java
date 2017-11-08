package io.nbikes;

import android.os.Bundle;

import io.nbikes.ui.PresenterCompliantActivity;
import io.nbikes.ui.map.MapFragment;

public class MainActivity extends PresenterCompliantActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new MapFragment())
                    .commitAllowingStateLoss();
        }
    }
}
