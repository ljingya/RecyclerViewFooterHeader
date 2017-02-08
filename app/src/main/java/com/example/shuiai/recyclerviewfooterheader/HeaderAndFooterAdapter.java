package com.example.shuiai.recyclerviewfooterheader;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/2/7
 */

public class HeaderAndFooterAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private static final int ITEM_HEADER = 100000;
    private static final int ITEM_FOOTER = 200000;
    private SparseArray<View> headerViews = new SparseArray<>();
    private SparseArray<View> footerViews = new SparseArray<>();
    private RecyclerView.Adapter innerAdapter;

    public HeaderAndFooterAdapter(RecyclerView.Adapter adapter) {
        this.innerAdapter = adapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            return new MyViewHolder(headerViews.get(viewType));
        } else if (footerViews.get(viewType) != null) {
            return new MyViewHolder(footerViews.get(viewType));
        }
        return (MyViewHolder) innerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (isHeaderPosition(position)) {
            return;
        }
        if (isFooterPosition(position)) {
            return;
        }
        innerAdapter.onBindViewHolder(holder, position - headerViews.size());
    }

    @Override
    public int getItemCount() {
        return headerViews.size() + innerAdapter.getItemCount() + footerViews.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return headerViews.keyAt(position);
        } else if (isFooterPosition(position)) {
            return footerViews.keyAt(position - headerViews.size() - innerAdapter.getItemCount());
        }
        return innerAdapter.getItemViewType(position - headerViews.size());
    }

    /**
     * 添加头布局
     *
     * @param view
     */
    public void addHeaderView(View view) {
        headerViews.put(headerViews.size() + ITEM_HEADER, view);
    }

    /**
     * 添加脚布局
     *
     * @param view
     */
    public void addFooterView(View view) {
        footerViews.put(footerViews.size() + ITEM_FOOTER, view);
    }

    /**
     * 判断当前位置是否是头布局
     *
     * @param position
     * @return
     */
    public boolean isHeaderPosition(int position) {
        return headerViews.size() > position;
    }

    /**
     * 判断当前位置是否是脚布局
     *
     * @param position
     * @return
     */
    public boolean isFooterPosition(int position) {
        return headerViews.size() + innerAdapter.getItemCount() <= position;
    }

    /**
     * 设置GridLayoutManger时的spansize
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        innerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (headerViews.get(viewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    } else if (footerViews.get(viewType) != null) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    /**
     * 设置瀑布流时的脚布局和头布局
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(MyViewHolder holder) {
        innerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderPosition(position) || isFooterPosition(position)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                lp.setFullSpan(true);
            }
        }
    }
}
