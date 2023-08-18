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
import edu.wkd.fakelocation.models.obj.Location;
import edu.wkd.fakelocation.util.CustomProgressDialog;
import edu.wkd.fakelocation.view.adapter.LocationAdapter;
import edu.wkd.fakelocation.view.adapter.IconAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureFragment extends Fragment {
    private RecyclerView rcvItem;
    private RecyclerView rcvLocation;
    private IconAdapter iconAdapter;
    private LocationAdapter locationAdapter;
    private List<Location> listLocation;
    private CustomProgressDialog dialog;
    private int page = 1;

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
        rcvItem = view.findViewById(R.id.rcv_icon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvItem.setLayoutManager(linearLayoutManager);
        iconAdapter = new IconAdapter(getActivity());
        rcvItem.setAdapter(iconAdapter);

        // --------- image location -----------
        rcvLocation = view.findViewById(R.id.rcv_location);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvLocation.setLayoutManager(gridLayoutManager);
        listLocation = new ArrayList<>();
        locationAdapter = new LocationAdapter(getActivity(), listLocation);
        rcvLocation.setAdapter(locationAdapter);

        getDataLocation();
    }

    private void getDataLocation() {
        dialog.show();
        ApiService.apiService.listLocation(page).enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                List<Location> listLocationResponse = response.body();
                locationAdapter.setListLocations(listLocationResponse);
                Log.d("zzzzzz", "ListLocation: " + listLocationResponse.toString());
                delayCancelDialog();
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.d("zzzzzzzzz", "ListLocation-ERROR: " + t.toString());
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