package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.databinding.LayoutItemCategoryBinding;
import edu.wkd.fakelocation.util.UtitInterface;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.IconViewHolder>{
    private Context context;
    private List<String> listCategory;
    private boolean isPressed;
    private UtitInterface utitInterface;

    public CategoryAdapter(Context context, List<String> listCategory, UtitInterface utitInterface) {
        this.context = context;
        this.listCategory = listCategory;
        this.utitInterface = utitInterface;
    }

    public void setList(List<String> listCategory) {
        this.listCategory = listCategory;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCategoryBinding binding = LayoutItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new IconViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        String category = listCategory.get(position);

        holder.binding.tvCategory.setText(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPressed) {
                    holder.binding.tvCategory.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
                    holder.binding.tvCategory.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                } else {
                    holder.binding.tvCategory.setBackgroundColor(ContextCompat.getColor(context, android.R.color.black));
                    holder.binding.tvCategory.setTextColor(ContextCompat.getColor(context, android.R.color.white));
                }
                isPressed = !isPressed;
                utitInterface.onclick(context, category);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listCategory != null) {
            return listCategory.size();
        }
        return 0;
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemCategoryBinding binding;
        public IconViewHolder(@NonNull LayoutItemCategoryBinding layoutItemCategoryBinding) {
            super(layoutItemCategoryBinding.getRoot());
            this.binding = layoutItemCategoryBinding;
        }
    }
}
