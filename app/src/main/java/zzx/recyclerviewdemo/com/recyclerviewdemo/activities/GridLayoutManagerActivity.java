package zzx.recyclerviewdemo.com.recyclerviewdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutTopItemAnimator;
import zzx.recyclerviewdemo.com.recyclerviewdemo.R;
import zzx.recyclerviewdemo.com.recyclerviewdemo.adapters.GridLayoutAdapter;
import zzx.recyclerviewdemo.com.recyclerviewdemo.adapters.NormalLayoutAdapter;
import zzx.recyclerviewdemo.com.recyclerviewdemo.decorations.DividerGridItemDecoration;

public class GridLayoutManagerActivity extends AppCompatActivity {
    private TextView tv5;
    private TextView remove2;
    private TextView add2;
    private RecyclerView recyclerView2;
    private GridLayoutAdapter mAdapter;
    private List<String> resList;
    private DividerGridItemDecoration dividerGridItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_manager);

        initViews();

        initListener();
    }

    private void initViews() {
        tv5 = (TextView) findViewById(R.id.tv5);
        remove2 = (TextView) findViewById(R.id.remove2);
        add2 = (TextView) findViewById(R.id.add2);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);

        //设置布局
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
//        //设置间隔
//        dividerGridItemDecoration = new DividerGridItemDecoration(this);
//        recyclerView2.addItemDecoration(dividerGridItemDecoration);
        //设置item动画
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        resList = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            resList.add((char) i + "");
        }

        mAdapter = new GridLayoutAdapter(this);
        mAdapter.setData(resList);

//        SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(mAdapter, recyclerView2);
//        animatorAdapter.getViewAnimator().setInitialDelayMillis(200);
        recyclerView2.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new GridLayoutAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast toast = null;
                if (toast != null) {
                    toast.setText(resList.get(position));
                } else {
                    toast = Toast.makeText(GridLayoutManagerActivity.this, resList.get(position), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        remove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        recyclerView2.setVisibility(View.GONE);
        super.onBackPressed();
    }
}
