package com.example.shuiai.recyclerviewfooterheader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author shuiai@dianjia.io
 * @Company 杭州木瓜科技有限公司
 * @date 2017/2/7
 */

public class DataAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private String[] titles;

    public DataAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView textView = holder.get(R.id.text);
        textView.setText(titles[position]+"");
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
