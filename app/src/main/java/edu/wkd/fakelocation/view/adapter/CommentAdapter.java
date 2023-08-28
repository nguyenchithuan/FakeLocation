package edu.wkd.fakelocation.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.data.database_local.shared_preferences.DataLocalManager;
import edu.wkd.fakelocation.databinding.LayoutItemCommentBinding;
import edu.wkd.fakelocation.models.obj.Comment;
import edu.wkd.fakelocation.models.response.CommentDeleteReponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private List<Comment> listComment;


    public CommentAdapter(Context context, List<Comment> listComment) {
        this.context = context;
        this.listComment = listComment;
    }

    public void setList(List<Comment> listComment) {
        this.listComment = listComment;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCommentBinding binding = LayoutItemCommentBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = listComment.get(position);
        if (comment == null) {
            return;
        }
        holder.binding.tvUsername.setText(comment.getUserName());
        holder.binding.tvComment.setText(comment.getNoidungComment());

        Glide.with(context)
                .load(comment.getLinkAvatar())
                .placeholder(R.drawable.img_white)
                .error(R.drawable.img_avt)
                .into(holder.binding.imgAvt);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this comment?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteComment(comment.getIdComment(), position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void deleteComment(int idComment, int position) {
        String strToken = DataLocalManager.getDataToken();
        ApiService.apiService.deleteComment(strToken, idComment).enqueue(new Callback<CommentDeleteReponse>() {
            @Override
            public void onResponse(Call<CommentDeleteReponse> call, Response<CommentDeleteReponse> response) {
                    // Lý do làm thế này là api nó trả 2 lại object khác nhau
                    // 1 là {"message": ""},
                    // 2 là {"error": ""}
                    try {
                        String message = response.body().getMessage();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.d("zzzzz", "deleteComment: " + message);
                        listComment.remove(position);
                        notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(context, "Can't delete other people's comments", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<CommentDeleteReponse> call, Throwable t) {
                Log.d("zzzzz", "deleteComment: " + t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listComment != null) {
            return listComment.size();
        }
        return 0;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        LayoutItemCommentBinding binding;

        public CommentViewHolder(@NonNull LayoutItemCommentBinding layoutItemCommentBinding) {
            super(layoutItemCommentBinding.getRoot());
            binding = layoutItemCommentBinding;
        }
    }
}
