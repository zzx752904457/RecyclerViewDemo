package zzx.recyclerviewdemo.com.recyclerviewdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.adapter.SlideInLeftAnimatorAdapter;
import zzx.recyclerviewdemo.com.recyclerviewdemo.R;
import zzx.recyclerviewdemo.com.recyclerviewdemo.adapters.StaggeredGridLayoutAdapter;
import zzx.recyclerviewdemo.com.recyclerviewdemo.decorations.DividerGridItemDecoration;

public class StaggeredGridLayoutManagerActivity extends AppCompatActivity {
    private TextView tv6;
    private TextView remove3;
    private TextView add3;
    private RecyclerView recyclerView3;
    private StaggeredGridLayoutAdapter mAdapter;
    private List<String> resList;
    private DividerGridItemDecoration dividerGridItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout_manager);

        initViews();

        initListener();
    }

    private void initViews() {
        tv6 = (TextView) findViewById(R.id.tv6);
        remove3 = (TextView) findViewById(R.id.remove3);
        add3 = (TextView) findViewById(R.id.add3);
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerView3);

        resList = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            resList.add((char) i + "");
        }

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView3.setLayoutManager(manager);
        mAdapter = new StaggeredGridLayoutAdapter(this, resList);

//        dividerGridItemDecoration = new DividerGridItemDecoration(this);
//        recyclerView3.addItemDecoration(dividerGridItemDecoration);


        recyclerView3.setItemAnimator(new DefaultItemAnimator());

        SlideInLeftAnimatorAdapter animatorAdapter = new SlideInLeftAnimatorAdapter(mAdapter, recyclerView3);
        animatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        recyclerView3.setAdapter(mAdapter);
    }

    private void initListener() {
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        remove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(1);
            }
        });

        mAdapter.setOnItemClickListener(new StaggeredGridLayoutAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast toast = null;
                if (toast != null) {
                    toast.setText(resList.get(position));
                } else {
                    toast = Toast.makeText(StaggeredGridLayoutManagerActivity.this, resList.get(position), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        recyclerView3.setVisibility(View.GONE);
        super.onBackPressed();
    }
}
