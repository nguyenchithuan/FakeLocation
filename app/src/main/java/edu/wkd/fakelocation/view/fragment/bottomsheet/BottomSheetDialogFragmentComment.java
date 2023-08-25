package edu.wkd.fakelocation.view.fragment.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.data.database_local.room.UserDatabase;
import edu.wkd.fakelocation.data.database_local.shared_preferences.DataLocalManager;
import edu.wkd.fakelocation.models.obj.Comment;
import edu.wkd.fakelocation.models.obj.Picture;
import edu.wkd.fakelocation.models.obj.User;
import edu.wkd.fakelocation.models.request.CommentRequest;
import edu.wkd.fakelocation.models.response.CommentResponse;
import edu.wkd.fakelocation.view.adapter.CommentAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetDialogFragmentComment extends BottomSheetDialogFragment {
    private Picture picture;
    private ImageView imgPhoto;
    private ImageView imgAvt;
    private EditText edComment;
    private ImageView imgSend;
    private RecyclerView rcvComment;
    private List<Comment> listComment;
    private CommentAdapter commentAdapter;

    public static BottomSheetDialogFragmentComment newInstance(Picture picture) {
        BottomSheetDialogFragmentComment sheetDialog = new BottomSheetDialogFragmentComment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("getdata", picture);
        sheetDialog.setArguments(bundle);
        return sheetDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            picture = (Picture) bundle.getSerializable("getdata");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet_fragment_comment, null);
        bottomSheetDialog.setContentView(view);
//        bottomSheetDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        bottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        init(view);


        return bottomSheetDialog;
    }

    private void init(View view) {
        imgPhoto = view.findViewById(R.id.img_photo);
        imgAvt = view.findViewById(R.id.img_avt);
        edComment = view.findViewById(R.id.ed_comment);
        imgSend = view.findViewById(R.id.img_send);
        rcvComment = view.findViewById(R.id.rcv_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvComment.setLayoutManager(linearLayoutManager);

        listComment = new ArrayList<>();
        commentAdapter = new CommentAdapter(getActivity(), listComment);
        rcvComment.setAdapter(commentAdapter);


        // Image puctures
        Glide.with(getActivity())
                .load(picture.getLinkImage())
                .placeholder(R.drawable.img_white)
                .into(imgPhoto);

        // Image avate comment
        User userLogin = UserDatabase.getInstance(getActivity()).userDao().getListUser().get(0);
        Glide.with(getActivity())
                .load(userLogin.getLinkAvatar())
                .placeholder(R.drawable.img_white)
                .error(R.drawable.img_avt)
                .into(imgAvt);
        
        getDataComment();

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommentPicture();
            }
        });
    }

    private void getDataComment() {
        ApiService.apiService.listComment(picture.getIdImage()).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                listComment = response.body();
                commentAdapter.setList(listComment);
                Log.d("zzzzz", "getDataComment: " + listComment.toString());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("zzzzz", "getDataComment-Error: ");
            }
        });
    }

    private void sendCommentPicture() {
        int idImage = picture.getIdImage();
        String strComment = edComment.getText().toString().trim();
        String strLinkImage = picture.getLinkImage();

        if(strComment.length() == 0) {
            return;
        }

        CommentRequest commentRequest = new CommentRequest(idImage, strComment, strLinkImage);

        String strToken = DataLocalManager.getDataToken();
        ApiService.apiService.postComment(strToken, commentRequest).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                CommentResponse commentResponse = response.body();
                listComment.add(commentResponse.getComment());
                commentAdapter.setList(listComment);
                edComment.setText("");
                Log.d("zzzzz", "postComment: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.d("zzzzz", "postComment-Error: ");
            }
        });
    }
}
