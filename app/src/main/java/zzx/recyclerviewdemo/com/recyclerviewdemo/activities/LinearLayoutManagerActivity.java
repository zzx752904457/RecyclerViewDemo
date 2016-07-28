package zzx.recyclerviewdemo.com.recyclerviewdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInLeftAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import zzx.recyclerviewdemo.com.recyclerviewdemo.R;
import zzx.recyclerviewdemo.com.recyclerviewdemo.adapters.NormalLayoutAdapter;
import zzx.recyclerviewdemo.com.recyclerviewdemo.decorations.DividerItemDecoration;

public class LinearLayoutManagerActivity extends AppCompatActivity {
    private TextView tv4;
    private TextView remove1;
    private TextView add1;
    private RecyclerView recyclerView1;
    private NormalLayoutAdapter mAdapter;
    private List<String> resList;
    private DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_manager);

        initViews();

        initListener();
    }

    private void initViews() {
        tv4 = (TextView) findViewById(R.id.tv4);
        remove1 = (TextView) findViewById(R.id.remove1);
        add1 = (TextView) findViewById(R.id.add1);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView1.addItemDecoration(dividerItemDecoration);
        //设置item动画
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        resList = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            resList.add((char) i + "");
        }

        mAdapter = new NormalLayoutAdapter(this);
        mAdapter.setData(resList);

        SlideInLeftAnimatorAdapter animatorAdapter = new SlideInLeftAnimatorAdapter(mAdapter, recyclerView1);
        animatorAdapter.getViewAnimator().setInitialDelayMillis(500);
        recyclerView1.setAdapter(mAdapter);
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new NormalLayoutAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast toast = null;
                if (toast != null) {
                    toast.setText(resList.get(position));
                } else {
                    toast = Toast.makeText(LinearLayoutManagerActivity.this, resList.get(position), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        remove1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeData(1);
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        recyclerView1.setVisibility(View.GONE);
        super.onBackPressed();
    }
}
