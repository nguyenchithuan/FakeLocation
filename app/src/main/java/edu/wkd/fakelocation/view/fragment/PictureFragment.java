package edu.wkd.fakelocation.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.api.ApiService;
import edu.wkd.fakelocation.models.obj.Categories;
import edu.wkd.fakelocation.models.obj.Location;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import edu.wkd.fakelocation.util.PaginationScrollListener;
import edu.wkd.fakelocation.view.adapter.LocationAdapter;
import edu.wkd.fakelocation.view.adapter.CategoryAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureFragment extends Fragment {
    private RecyclerView rcvCategories;
    private RecyclerView rcvLocation;
    private CategoryAdapter categoryAdapter;
    private LocationAdapter locationAdapter;
    private List<Location> listLocation;
    private List<String> listCategory;
    private CustomProgressDialog dialog;
    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;

    // https://i.ibb.co/98K3LFz/Rectangle-161.png
    // https://i.ibb.co/18fCCGZ/Rectangle-162.png


    public static PictureFragment newInstance() {
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dialog = new CustomProgressDialog(getActivity(), 1);

        // ------- category -----------
        rcvCategories = view.findViewById(R.id.rcv_categories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvCategories.setLayoutManager(linearLayoutManager);
        listCategory = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), listCategory);
        rcvCategories.setAdapter(categoryAdapter);

        // --------- image location -----------
        rcvLocation = view.findViewById(R.id.rcv_location);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvLocation.setLayoutManager(gridLayoutManager);
        listLocation = new ArrayList<>();
        locationAdapter = new LocationAdapter(getActivity(), listLocation);
        rcvLocation.setAdapter(locationAdapter);

        // Xem tincoder bài phân trang để hiểu thêm
        rcvLocation.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItems() { // nếu scroll trong recycleview mà kịch thì nó sẽ chạy hàm này
                // Đăng load page thì tăng page lên 1
                isLoading = true;
                currentPage++;
                loadNextPage(currentPage);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });

        getDataPageLocation(currentPage);

        getDataCategories();
    }

    private void loadNextPage(int currentPage) {
        getDataPageLocation(currentPage);
    }

    private void getDataPageLocation(int currentPage) {
        dialog.show();
        ApiService.apiService.listLocation(currentPage).enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {

                List<Location> listLocationResponse = response.body();
                listLocation.addAll(listLocationResponse);
                locationAdapter.setListLocations(listLocation);
                Log.d("zzzzzz", "ListLocation " + currentPage + ":" + listLocation.toString());
                delayCancelDialog();
                isLoading = false;
                // Kiểm tra nếu hết dữ liệu mà nhỏ hơn 14 thì tức là hết trang
                // mỗi page có 14 data
                if(listLocationResponse.size() < 14) {
                    isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.d("zzzzzzzzz", "ListLocation-ERROR: " + t.toString());
                delayCancelDialog();
            }
        });
    }

    private void getDataCategories() {
        dialog.show();
        ApiService.apiService.listCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Categories categories = response.body();
                listCategory = categories.getCategories();
                categoryAdapter.setList(listCategory);

                Log.d("zzzzzz", "getDataCategories: " +  categories.toString());
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.d("zzzzzzzzz", "getDataCategories-ERROR: " + t.toString());
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
        }, 1000);
    }
}