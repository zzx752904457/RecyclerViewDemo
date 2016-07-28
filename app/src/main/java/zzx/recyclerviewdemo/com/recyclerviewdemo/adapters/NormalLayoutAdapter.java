package zzx.recyclerviewdemo.com.recyclerviewdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zzx.recyclerviewdemo.com.recyclerviewdemo.R;

/**
 * Created by admin on 2016/7/27.
 */
public class NormalLayoutAdapter extends RecyclerView.Adapter<NormalLayoutAdapter.MyViewHolder> {
    private Context context;
    private List<String> resList;
    private MyItemClickListener itemClickListener;

    public NormalLayoutAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<String> resList){
        this.resList = resList;
        notifyDataSetChanged();
    }

    public void addData(int position){
        resList.add(position, "鲜鲜哥");
        notifyItemInserted(position);
//        notifyDataSetChanged();
    }

    public void removeData(int position){
        resList.remove(position);
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
