package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.wkd.fakelocation.R;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder>{
    private Context context;

    public IconAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_icon, parent, false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon);
        }
    }
}
