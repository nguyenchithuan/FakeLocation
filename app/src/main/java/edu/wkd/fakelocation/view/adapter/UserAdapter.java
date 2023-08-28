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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.databinding.LayoutItemNewUserBinding;
import edu.wkd.fakelocation.models.obj.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> listUser;

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemNewUserBinding binding = LayoutItemNewUserBinding.inflate(LayoutInflater.from(context), parent, false);
        return  new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        if(user == null) {
            return;
        }
        holder.binding.tvUsername.setText(user.getUserName());
        // Chuyển createAt về dạng dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.binding.tvCreateAt.setText(dateFormat.format(new Date(user.getCreateAt())));
        Glide.with(context).load(user.getLinkAvatar())
                .placeholder(R.drawable.img_white)
                .error(R.drawable.img_avt)
                .into(holder.binding.imgUser);
    }

    @Override
    public int getItemCount() {
        if(listUser != null) {
            return listUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        LayoutItemNewUserBinding binding;
        public UserViewHolder(@NonNull LayoutItemNewUserBinding layoutItemNewUserBinding) {
            super(layoutItemNewUserBinding.getRoot());
            binding = layoutItemNewUserBinding;
        }
    }
}
