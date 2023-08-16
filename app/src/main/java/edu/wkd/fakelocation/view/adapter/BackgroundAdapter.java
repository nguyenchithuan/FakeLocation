package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.wkd.fakelocation.R;

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.BackgroundViewHolder>{
    private Context context;

    public BackgroundAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BackgroundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_background, parent, false);
        return new BackgroundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BackgroundViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class BackgroundViewHolder extends RecyclerView.ViewHolder {
        public BackgroundViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
