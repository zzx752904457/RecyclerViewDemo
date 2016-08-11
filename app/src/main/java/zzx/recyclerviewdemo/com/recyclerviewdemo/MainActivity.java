package zzx.recyclerviewdemo.com.recyclerviewdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import zzx.recyclerviewdemo.com.recyclerviewdemo.activities.GridLayoutManagerActivity;
import zzx.recyclerviewdemo.com.recyclerviewdemo.activities.LinearLayoutManagerActivity;
import zzx.recyclerviewdemo.com.recyclerviewdemo.activities.StaggeredGridLayoutManagerActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initListener();
    }

    private void initListener() {
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearLayoutManagerActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, tv1, "tv1");
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridLayoutManagerActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, tv2, "tv2");
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaggeredGridLayoutManagerActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, tv3, "tv3");
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    private void initViews() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
    }


}
