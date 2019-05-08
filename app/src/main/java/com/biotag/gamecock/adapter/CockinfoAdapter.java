package com.biotag.gamecock.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biotag.gamecock.R;

import java.util.ArrayList;

/**
 * Created by Lxh on 2017/8/4.
 */

public class CockinfoAdapter extends RecyclerView.Adapter<CockinfoAdapter.CockinfoItemHolder> {

    private Context context;
    private ArrayList<String> mDatas = new ArrayList<>();
    public CockinfoAdapter(Context context, ArrayList<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;

    }

    @Override
    public CockinfoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_cockbasic_info_item,parent,false);
        CockinfoItemHolder holder = new CockinfoItemHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CockinfoItemHolder holder, int position) {
        holder.tv_num.setText(mDatas.get(position));
//        holder.tv_content.setText("lalllalallalal");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class CockinfoItemHolder extends RecyclerView.ViewHolder {
        TextView tv_num;
//        TextView tv_content;
        public CockinfoItemHolder(View itemView) {
            super(itemView);
            tv_num = (TextView)itemView.findViewById(R.id.tv_num);
//            tv_content = (TextView)itemView.findViewById(tv_content);
        }
    }
}
