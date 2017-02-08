package com.example.shuiai.recyclerviewfooterheader;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/2/7
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public View itemView;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public <T extends View> T get(int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) itemView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            itemView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
