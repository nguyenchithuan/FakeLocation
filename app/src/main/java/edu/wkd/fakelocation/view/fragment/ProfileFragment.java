package edu.wkd.fakelocation.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.api.ApiService;
import edu.wkd.fakelocation.models.obj.Picture;
import edu.wkd.fakelocation.util.Utit;
import edu.wkd.fakelocation.view.adapter.PictureAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private RecyclerView rcvYourPictures;
    private PictureAdapter yourPicturesAdapter;
    private List<Picture> listYourPictures;

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
    }

    private void init(View view) {
        rcvYourPictures = view.findViewById(R.id.rcv_your_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvYourPictures.setLayoutManager(gridLayoutManager);
        rcvYourPictures.setFocusable(false);
        rcvYourPictures.setNestedScrollingEnabled(false);
        listYourPictures = new ArrayList<>();
        yourPicturesAdapter = new PictureAdapter(getActivity(), listYourPictures, R.layout.layout_item_latest_pictures);
        rcvYourPictures.setAdapter(yourPicturesAdapter);
    }

    private void getListYourPictures() {
        ApiService.apiService.listYourPictures(Utit.TOKEN).enqueue(new Callback<List<Picture>>() {
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
}