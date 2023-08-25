package edu.wkd.fakelocation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.models.obj.Picture;
import edu.wkd.fakelocation.util.UtitInterface;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.FakeLocationHolder>{
    private final Context context;
    private List<Picture> listPictures;
    private int layoutResource;
    private UtitInterface utitInterface;

    public PictureAdapter(Context context, List<Picture> listPictures, int layoutResource) {
        this.context = context;
        this.listPictures = listPictures;
        this.layoutResource = layoutResource;
    }

    public PictureAdapter(Context context, List<Picture> listPictures, int layoutResource, UtitInterface utitInterface) {
        this.context = context;
        this.listPictures = listPictures;
        this.layoutResource = layoutResource;
        this.utitInterface = utitInterface;
    }

    public void setList(List<Picture> listPictures) {
        this.listPictures = listPictures;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FakeLocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);
        return new FakeLocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FakeLocationHolder holder, int position) {
        Picture picture = listPictures.get(position);
        if(picture == null) {
            return;
        }

        holder.tvCountComment.setText(picture.getCommentCount() + "");

        Glide.with(context)
                .load(picture.getLinkImage())
                .centerCrop()
                .placeholder(R.drawable.img_white)
                .into(holder.imgPhoto);

        holder.linearLayoutGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utitInterface != null) {
                    utitInterface.onclick(context, picture);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listPictures != null) {
            return listPictures.size();
        }
        return 0;
    }

    public static class FakeLocationHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPhoto;
        private final TextView tvCountComment;
        private final LinearLayout linearLayoutGroup;
        public FakeLocationHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            tvCountComment  = itemView.findViewById(R.id.tv_count_commnet);
            linearLayoutGroup  = itemView.findViewById(R.id.linearLayout_group);
        }
    }
}
