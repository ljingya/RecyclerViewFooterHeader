package com.example.shuiai.recyclerviewfooterheader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String[] titles = {
            "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C",
            "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C", "A", "B", "C"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        DataAdapter dataAdapter = new DataAdapter(this, titles);
        HeaderAndFooterAdapter mHeaderAndFooterAdapter = new HeaderAndFooterAdapter(dataAdapter);
        TextView textView = new TextView(this);
        textView.setText("head1");
        TextView textView3 = new TextView(this);
        textView3.setText("head3");
        TextView textView1 = new TextView(this);
        textView1.setText("footer1");
        mHeaderAndFooterAdapter.addHeaderView(textView);
        mHeaderAndFooterAdapter.addHeaderView(textView3);
        mHeaderAndFooterAdapter.addFooterView(textView1);
        recyclerView.setAdapter(mHeaderAndFooterAdapter);
    }
}
