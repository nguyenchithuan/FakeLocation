package edu.wkd.fakelocation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.api.ApiService;
import edu.wkd.fakelocation.models.obj.ChangeBackground;
import edu.wkd.fakelocation.models.obj.User;
import edu.wkd.fakelocation.models.response.ListNewUserResponse;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import edu.wkd.fakelocation.view.adapter.ChangeBackgroundAdapter;
import edu.wkd.fakelocation.view.adapter.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private RecyclerView rcvLatestPictures;
    private RecyclerView rcvListNewUser;
    private List<ChangeBackground> changeBackgroundList;
    private List<User> listUser;
    private ChangeBackgroundAdapter sliderAdapter;
    private ChangeBackgroundAdapter latestPicturesAdapter;
    private UserAdapter userAdapter;
    private Button btnBack;
    private Button btnNext;
    private CustomProgressDialog dialog;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);

        getListChageBackGroundSliderAndLatestPictures();

        getListNewUser();
    }



    private void init(View view) {
        mViewPager2 = view.findViewById(R.id.view_pager_2);
        btnBack = view.findViewById(R.id.btn_back);
        btnNext = view.findViewById(R.id.btn_next);
        rcvLatestPictures = view.findViewById(R.id.rcv_latest_pictures);
        rcvListNewUser = view.findViewById(R.id.rcv_list_new_user);
        dialog = new CustomProgressDialog(getContext(), 1);

        // ------------------ Image slider --------------------
        // comfig cho viewpage 2 để hiển thị image slider đẹp hơn
        comfigViewPager2();

        // Chú ý latest pictures, slider dùng adapter giống nhau thay layout_item
        changeBackgroundList = new ArrayList<>();
        sliderAdapter = new ChangeBackgroundAdapter(getActivity(), changeBackgroundList, R.layout.layout_item_change_background_slider);
        mViewPager2.setAdapter(sliderAdapter);


        // ------------------ latest pictures --------------------
        latestPicturesAdapter = new ChangeBackgroundAdapter(getActivity(), changeBackgroundList, R.layout.layout_item_latest_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvLatestPictures.setLayoutManager(gridLayoutManager);
        rcvLatestPictures.setFocusable(false);
        rcvLatestPictures.setNestedScrollingEnabled(false);
        rcvLatestPictures.setAdapter(latestPicturesAdapter);

        // ----------------- list new user -----------------

        try {
            listUser = new ArrayList<>();
            userAdapter = new UserAdapter(getActivity(), listUser);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcvListNewUser.setLayoutManager(linearLayoutManager);
            rcvListNewUser.setFocusable(false);
            rcvListNewUser.setNestedScrollingEnabled(false);
            rcvListNewUser.setAdapter(userAdapter);
        } catch (Exception e) {
            Log.d("zzzzz", "init: " + e.getMessage());
        }
    }

    private void comfigViewPager2() {
        // setting viewpager2
        mViewPager2.setOffscreenPageLimit(3); // hiển thị 3 ảnh
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);
        // khởi tạo
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40)); // cách nhau 40 px
        // tạo 2 image nhỏ hơn image ở giữa
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        mViewPager2.setPageTransformer(compositePageTransformer);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = mViewPager2.getCurrentItem();
                mViewPager2.setCurrentItem(index - 1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = mViewPager2.getCurrentItem();
                mViewPager2.setCurrentItem(index + 1);
            }
        });
    }

    private void getListChageBackGroundSliderAndLatestPictures() {
        dialog.show();
        ApiService.apiService.listChangeBackground().enqueue(new Callback<List<ChangeBackground>>() {
            @Override
            public void onResponse(Call<List<ChangeBackground>> call, Response<List<ChangeBackground>> response) {
                List<ChangeBackground> changeBackgroundListReponse = response.body();
                // Loading image slider
                sliderAdapter.setChangeBackgroundList(changeBackgroundListReponse);
                // Loading latest pictures
                latestPicturesAdapter.setChangeBackgroundList(changeBackgroundListReponse);
                Log.d("zzzzzz", "listChangeBackground: " + changeBackgroundListReponse.get(0).toString());
                delayCancelDialog();

            }

            @Override
            public void onFailure(Call<List<ChangeBackground>> call, Throwable t) {
                Log.d("zzzzzzzzz", "listChangeBackground-ERROR: " + t.toString());
                delayCancelDialog();
            }
        });
    }

    private void getListNewUser() {
        dialog.show();
        ApiService.apiService.listNewUsers().enqueue(new Callback<ListNewUserResponse>() {
            @Override
            public void onResponse(Call<ListNewUserResponse> call, Response<ListNewUserResponse> response) {
                ListNewUserResponse listNewUserResponse = response.body();
                userAdapter.setListUser(listNewUserResponse.getNewUsers());
                Log.d("zzzzzz", "ListUser: " + listNewUserResponse.getNewUsers().toString());
                delayCancelDialog();
            }

            @Override
            public void onFailure(Call<ListNewUserResponse> call, Throwable t) {
                Log.d("zzzzzzzzz", "ListUser-ERROR: " + t.toString());
                delayCancelDialog();
            }
        });
    }

    public void delayCancelDialog() {
        // Làm cho app mềm mại hơn không khựng
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        }, 2000);
    }
}