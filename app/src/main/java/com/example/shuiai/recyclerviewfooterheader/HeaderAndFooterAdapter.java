package com.example.shuiai.recyclerviewfooterheader;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
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
    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<>();
    private RecyclerView.Adapter innerAdapter;

    public HeaderAndFooterAdapter(RecyclerView.Adapter adapter) {
        this.innerAdapter = adapter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType) != null) {
            innerAdapter.createViewHolder(parent,)
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

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
}
