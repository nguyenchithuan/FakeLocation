package edu.wkd.fakelocation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.wkd.fakelocation.R;
import edu.wkd.fakelocation.view.adapter.BackgroundAdapter;
import edu.wkd.fakelocation.view.adapter.IconAdapter;

public class PictureFragment extends Fragment {
    private RecyclerView rcvItem;
    private RecyclerView rcvBackground;
    private IconAdapter iconAdapter;
    private BackgroundAdapter backgroundAdapter;

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
        rcvItem = view.findViewById(R.id.rcv_icon);
        rcvBackground = view.findViewById(R.id.rcv_background);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        rcvItem.setLayoutManager(linearLayoutManager);
        rcvBackground.setLayoutManager(gridLayoutManager);

        iconAdapter = new IconAdapter(getActivity());
        backgroundAdapter = new BackgroundAdapter(getActivity());

        rcvItem.setAdapter(iconAdapter);
        rcvBackground.setAdapter(backgroundAdapter);
    }
}