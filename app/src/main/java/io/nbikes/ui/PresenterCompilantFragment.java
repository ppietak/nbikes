package io.nbikes.ui;


import android.support.v4.app.Fragment;

import io.nbikes.App;

abstract public class PresenterCompilantFragment<T extends Presenter> extends Fragment implements PresenterCompliantView {

    abstract protected T getPresenter();

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().bind(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().unbind();
    }

    @Override
    public void registerPresenter(Presenter presenter) {
        ((PresenterCompliantActivity) getActivity()).registerPresenter(presenter);
    }

    protected App getApp() {
        return (App) getActivity().getApplication();
    }
}
