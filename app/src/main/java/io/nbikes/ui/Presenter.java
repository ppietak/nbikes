package io.nbikes.ui;

abstract public class Presenter<T extends PresenterCompliantView> {
    private T view;

    public Presenter(T view) {
        this.view = view;
    }

    /**
     * @return Whether action has been handled
     */
    public boolean onBackPressed() {
        return false;
    }

    public void bind(T view) {
        this.view = view;
        this.view.registerPresenter(this);
        this.afterBind();
    }

    public void unbind() {
        this.view = null;
        this.afterUnbind();
    }

    protected void afterBind() {}

    protected void afterUnbind() {}

    protected T getView() {
        return this.view;
    }

    protected boolean hasView() {
        return this.view != null;
    }
}
