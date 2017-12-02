package io.nbikes.ui.place.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nbikes.R;
import io.nbikes.data.model.Place;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {
    private List<Place> items;

    public PlaceListAdapter() {
        this.items = new ArrayList<>();
    }

    public void load(List<Place> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    public Place getItem(int position) {
        return items.get(position);
    }

    @Override
    public PlaceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.placelist_item, null);
        return new PlaceListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = getItem(position);

        holder.label.setText(place.getName());
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.placelist_item_label)
        public TextView label;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
