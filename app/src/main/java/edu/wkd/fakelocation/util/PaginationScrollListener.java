package edu.wkd.fakelocation.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    private GridLayoutManager gridLayoutManager;

    public PaginationScrollListener(GridLayoutManager gridLayoutManager) {
        this.gridLayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        // đếm các item nhìn thấy trên màn hình
        int visibleItemCount = gridLayoutManager.getChildCount();
        // tổng số item có trong 1 page
        int totalItemCount = gridLayoutManager.getItemCount();
        // đếm số item đầu tiên không nhìn thấy trong màn hình
        int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

        if(isLoading() || isLastPage()) {
            return;
        }

        if(firstVisibleItemPosition >= 0 && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
            loadMoreItems();
        }

    }

    public abstract void loadMoreItems(); // được gọi khi load page tiếp theo
    public abstract boolean isLoading(); // kiểm tra có đang load hay không
    public abstract boolean isLastPage(); // tra xem có đến trang cuối cùng hay không
}
