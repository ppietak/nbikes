package io.nbikes.ui.place.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nbikes.R;
import io.nbikes.data.model.Place;
import io.nbikes.di.component.DaggerPlaceListComponent;
import io.nbikes.di.module.PlaceListModule;
import io.nbikes.ui.core.PresenterCompilantFragment;
import io.nbikes.ui.place.list.view.PlaceListAdapter;

public class PlaceListFragment extends PresenterCompilantFragment<PlaceListPresenter> implements PlaceListView {
    private PlaceListAdapter listAdapter;

    @Inject
    public Bus bus;

    @Inject
    public PlaceListPresenter presenter;

    @BindView(R.id.placelist_list)
    public RecyclerView list;

    @Override
    protected PlaceListPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerPlaceListComponent.builder()
                .appComponent(getApp().getAppComponent())
                .placeListModule(new PlaceListModule(this))
                .build()
                .inject(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        list.setLayoutManager(mLayoutManager);
        list.addItemDecoration(new AdapterDecoration(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.placelist, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void loadPlaceList(List<Place> places) {
        if (listAdapter == null) {
            listAdapter = new PlaceListAdapter();
            list.setHasFixedSize(true);
        }

        list.setVisibility(View.VISIBLE);
        list.setAdapter(listAdapter);
        listAdapter.load(places);
    }

    private class AdapterDecoration extends RecyclerView.ItemDecoration {
        private Drawable divider;

        AdapterDecoration(Context context) {
            divider = ContextCompat.getDrawable(context, R.drawable.shape_separator);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }
    }
}
