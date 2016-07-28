package zzx.recyclerviewdemo.com.recyclerviewdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zzx.recyclerviewdemo.com.recyclerviewdemo.R;

/**
 * Created by admin on 2016/7/28.
 */
public class StaggeredGridLayoutAdapter extends RecyclerView.Adapter<StaggeredGridLayoutAdapter.MyViewHolder> {

    private Context context;
    private List<String> resList;
    private MyItemClickListener itemClickListener;
    private List<Integer> mHeights;
    private List<Integer> mWidths;

    public StaggeredGridLayoutAdapter(Context context, List<String> resList) {
        this.context = context;
        this.resList = resList;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < resList.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
        mWidths = new ArrayList<Integer>();
        for (int i = 0; i < resList.size(); i++) {
            mWidths.add((int) (100 + Math.random() * 300));
        }
    }

    public void setOnItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void addData(int position) {
        resList.add(position, "鲜鲜哥");
        mHeights.add((int) (100 + Math.random() * 300));
        mWidths.add((int) (100 + Math.random() * 300));
        notifyItemInserted(position);
//        notifyDataSetChanged();
    }

    public void removeData(int position) {
        resList.remove(position);
        mHeights.remove(new Random().nextInt(resList.size()));
        mWidths.remove(new Random().nextInt(resList.size()));
        notifyItemRemoved(position);
//        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id_num = (TextView) holder.itemView.findViewById(R.id.id_num);

        ViewGroup.LayoutParams lp = holder.id_num.getLayoutParams();
        lp.height = mHeights.get(position);
//        lp.width = mWidths.get(position);

        holder.id_num.setLayoutParams(lp);
        holder.id_num.setText(resList.get(position) + "");
    }

    @Override
    public int getItemCount() {
        if (resList != null) {
            return resList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView id_num;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }
}
