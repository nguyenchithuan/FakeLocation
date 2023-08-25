package edu.wkd.fakelocation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.data.api.ApiService;
import edu.wkd.fakelocation.data.database_local.room.UserDatabase;
import edu.wkd.fakelocation.data.database_local.shared_preferences.DataLocalManager;
import edu.wkd.fakelocation.models.obj.Picture;
import edu.wkd.fakelocation.models.obj.Profile;
import edu.wkd.fakelocation.models.obj.User;
import edu.wkd.fakelocation.view.activity.LogInNowActivity;
import edu.wkd.fakelocation.view.adapter.PictureAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private RecyclerView rcvYourPictures;
    private PictureAdapter yourPicturesAdapter;
    private List<Picture> listYourPictures;
    private Profile profileUser;
    private TextView tvCountPictures;
    private TextView tvCountComments;
    private TextView tvCountViews;
    private ImageView imgAvt;
    private Button btnLogout;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        getListYourPictures();

        getProfileUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDatabase.getInstance(getActivity()).userDao().deleteAllUser();
                DataLocalManager.setDataToken("");

                Intent intent = new Intent(getActivity(), LogInNowActivity.class);
                startActivity(intent);
                getActivity().finishAffinity();
            }
        });
    }


    private void init(View view) {
        tvCountPictures = view.findViewById(R.id.tv_count_pictures);
        tvCountComments = view.findViewById(R.id.tv_count_comment);
        tvCountViews = view.findViewById(R.id.tv_count_view);
        imgAvt = view.findViewById(R.id.img_avt);
        btnLogout = view.findViewById(R.id.btn_log_out);

        rcvYourPictures = view.findViewById(R.id.rcv_your_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvYourPictures.setLayoutManager(gridLayoutManager);
        rcvYourPictures.setFocusable(false);
        rcvYourPictures.setNestedScrollingEnabled(false);
        listYourPictures = new ArrayList<>();
        yourPicturesAdapter = new PictureAdapter(getActivity(), listYourPictures, R.layout.layout_item_pictures);
        rcvYourPictures.setAdapter(yourPicturesAdapter);
    }

    private void getListYourPictures() {
        User userLogin = UserDatabase.getInstance(getActivity()).userDao().getListUser().get(0);
        String strToken = DataLocalManager.getDataToken();
        ApiService.apiService.listYourPictures(strToken, userLogin.getId()).enqueue(new Callback<List<Picture>>() {
            @Override
            public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
                List<Picture> list = response.body();
                yourPicturesAdapter.setList(list);
                Log.d("zzzz", "listYourPictures: " +list.toString());
            }

            @Override
            public void onFailure(Call<List<Picture>> call, Throwable t) {
                Log.d("zzzz", "listYourPictures-ERROR: " + t.toString());
            }
        });
    }

    private void getProfileUser() {
        User userLogin = UserDatabase.getInstance(getActivity()).userDao().getListUser().get(0);
        String strToken = DataLocalManager.getDataToken();
        ApiService.apiService.profileUser(strToken, userLogin.getId()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                profileUser = response.body();
                Log.d("zzzz", "getProfileUser: " + profileUser.toString());

                // cập nhật giao diện ở luồng chính không sẽ bị lỗi
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        tvCountPictures.setText(profileUser.getImageCount() + "");
                        tvCountComments.setText(profileUser.getCommenCount() + "");
                        Glide.with(getContext())
                                .load(profileUser.getLinkAvatar())
                                .placeholder(R.drawable.img_white)
                                .error(R.drawable.img_avt)
                                .into(imgAvt);
                    }
                });
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("zzzz", "getProfileUser-ERROR: " + t.toString());
            }
        });
    }

}