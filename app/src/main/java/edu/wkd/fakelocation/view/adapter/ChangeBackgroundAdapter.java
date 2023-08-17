package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.models.obj.ChangeBackground;

public class ChangeBackgroundAdapter extends RecyclerView.Adapter<ChangeBackgroundAdapter.PhotoViewHolder> {
    private final Context context;
    private List<ChangeBackground> changeBackgroundList;
    private int layoutResource;

    public ChangeBackgroundAdapter(Context context, List<ChangeBackground> changeBackgroundList, int layoutResource) {
        this.context = context;
        this.changeBackgroundList = changeBackgroundList;
        this.layoutResource = layoutResource;
    }

    public void setChangeBackgroundList(List<ChangeBackground> list) {
        changeBackgroundList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        ChangeBackground changeBackground = changeBackgroundList.get(position);
        if(changeBackground == null) {
            return;
        }

        holder.tvCountComment.setText(changeBackground.getCommentCount() + "");

        Glide.with(context)
                .load(changeBackground.getLinkImage())
                .centerCrop()
                .placeholder(R.drawable.img_white)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        if(changeBackgroundList != null) {
            return changeBackgroundList.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPhoto;
        private final TextView tvCountComment;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvCountComment  = itemView.findViewById(R.id.tv_count_commnet);
        }
    }
}
