package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.databinding.LayoutItemLocationBinding;
import edu.wkd.fakelocation.models.obj.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> implements Filterable {
    private Context context;
    private List<Location> listLocations;
    private List<Location> listLocationsTemp;

    public LocationAdapter(Context context, List<Location> listLocations) {
        this.context = context;
        this.listLocations = listLocations;
        this.listLocationsTemp = listLocations;
    }

    public void setListLocations(List<Location> listLocations) {
        this.listLocations = listLocations;
        this.listLocationsTemp = listLocations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemLocationBinding binding = LayoutItemLocationBinding.inflate(LayoutInflater.from(context), parent, false);
        return new LocationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = listLocations.get(position);
        if (location == null) {
            return;
        }
        Glide.with(context)
                .load(location.getLink())
                .placeholder(R.drawable.img_white)
                .error("https://i.ibb.co/18fCCGZ/Rectangle-162.png")
                .into(holder.binding.imgLocation);
    }

    @Override
    public int getItemCount() {
        if (listLocations != null) {
            return listLocations.size();
        }
        return 0;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemLocationBinding binding;

        public LocationViewHolder(@NonNull LayoutItemLocationBinding layoutItemLocationBinding) {
            super(layoutItemLocationBinding.getRoot());
            binding = layoutItemLocationBinding;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    listLocations = listLocationsTemp;
                } else {
                    List<Location> list = new ArrayList<>();
                    for (Location location : listLocationsTemp) {
                        if (location.getLocation().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(location);
                        }
                    }
                    listLocations = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listLocations;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listLocations = (List<Location>) filterResults.values;
                Log.d("zzzzz", "publishResults: " + listLocations.toString());
                notifyDataSetChanged();
            }
        };
    }
}
