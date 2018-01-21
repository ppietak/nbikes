package io.nbikes.ui.place.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nbikes.R;
import io.nbikes.ui.place.list.event.PlaceSelectedEvent;
import io.nbikes.data.model.Place;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> implements View.OnClickListener {
    private Bus bus;
    private List<Place> items;

    public PlaceListAdapter(Bus bus) {
        this.bus = bus;
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

        holder.title.setTag(position);
        holder.title.setOnClickListener(this);
        holder.title.setText(place.getName());
        holder.subtitle.setText(place.getCountryName());
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
        public TextView title;

        @BindView(R.id.placelist_item_sublabel)
        public TextView subtitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onClick(View view) {
        Integer position = (Integer) view.getTag();
        bus.post(new PlaceSelectedEvent(getItem(position)));
    }
}
