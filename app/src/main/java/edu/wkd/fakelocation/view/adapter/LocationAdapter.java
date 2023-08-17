package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.models.obj.Location;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder>{
    private Context context;
    private List<Location> listLocations;

    public LocationAdapter(Context context, List<Location> listLocations) {
        this.context = context;
        this.listLocations = listLocations;
    }

    public void setListLocations(List<Location> listLocations) {
        this.listLocations = listLocations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        Location location = listLocations.get(position);
        if(location == null) {
            return;
        }
        Glide.with(context)
                .load(location.getLink())
                .placeholder(R.drawable.img_white)
                .error("https://i.ibb.co/18fCCGZ/Rectangle-162.png")
                .into(holder.imgLocation);
    }

    @Override
    public int getItemCount() {
        if(listLocations != null) {
            return listLocations.size();
        }
        return 0;
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLocation;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLocation = itemView.findViewById(R.id.img_location);
        }
    }
}
