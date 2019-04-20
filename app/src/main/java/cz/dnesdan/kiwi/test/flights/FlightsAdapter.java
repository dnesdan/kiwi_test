package cz.dnesdan.kiwi.test.flights;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cz.dnesdan.kiwi.test.R;
import cz.dnesdan.kiwi.test.data.KiwiApiInterface;
import cz.dnesdan.kiwi.test.data.model.Flight;

/**Adapter which displays information about flight**/
public class FlightsAdapter extends RecyclerView.Adapter<FlightsAdapter.ViewHolder> {

    private static final String LOG_TAG = FlightsAdapter.class.getSimpleName();

    private List<Flight> list;

    public FlightsAdapter(List<Flight> list) {
        this.list = new ArrayList<>();
        addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight = list.get(position);

        holder.tvDestination.setText(flight.getCityTo() + " (" + flight.getCountryTo().getName() + ")");
        holder.tvFlyDate.setText(flight.getaTime());
        holder.tvPrice.setText(flight.getPrice().toString() + " Kč");
        holder.tvFlyFrom.setText(flight.getCountryFrom().getCode() + " (" + flight.getCityFrom() + ")");
        holder.tvFlightDuration.setText(flight.getFlyDuration());
        Log.d(LOG_TAG, KiwiApiInterface.IMAGE_BASE_URL + flight.getMapIdto() + ".jpg");
        Glide.with(holder.ivDestination).load(KiwiApiInterface.IMAGE_BASE_URL + flight.getMapIdto() + ".jpg")
                .into(holder.ivDestination);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //clear all elements of the recycler
    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Flight> update) {
        list.addAll(update);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_destination)
        ImageView ivDestination;
        @BindView(R.id.tv_destination)
        TextView tvDestination;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_fly_date)
        TextView tvFlyDate;
        @BindView(R.id.tv_fly_from)
        TextView tvFlyFrom;
        @BindView(R.id.tv_duration)
        TextView tvFlightDuration;

        View item;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.item = view;
            ButterKnife.bind(this, view);
        }
    }
}
