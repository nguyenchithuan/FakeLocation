package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.wkd.fakelocation.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.IconViewHolder>{
    private Context context;
    private List<String> listCategory;

    public CategoryAdapter(Context context, List<String> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    public void setList(List<String> listCategory) {
        this.listCategory = listCategory;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_category, parent, false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        String category = listCategory.get(position);

        holder.tvCategory.setText(category);
    }

    @Override
    public int getItemCount() {
        if(listCategory != null) {
            return listCategory.size();
        }
        return 0;
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
        }
    }
}
