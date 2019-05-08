package com.biotag.gamecock.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.biotag.gamecock.R;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Lxh on 2017/8/3.
 */

public class CockSecondaryInfoFragment extends SupportFragment {

    private RecyclerView rcv;
    private ArrayList<String> strList = new ArrayList<>();
    private InfoAdapter adapter;
    public static CockSecondaryInfoFragment newInstance(String json) {

        Bundle args = new Bundle();
        args.putString("cocksecondinfo",json);
        CockSecondaryInfoFragment fragment = new CockSecondaryInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String str;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        str = bundle.getString("cocksecondinfo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocksecond_info,container,false);
        rcv = (RecyclerView)view.findViewById(R.id.cocksecondrcv);
        initdata();
        initadapter();
        return view;
    }


    private void initdata() {
        for (int i = 0; i <100 ; i++) {
            strList.add("item"+i+str);
        }
    }
    private void initadapter(){
        adapter = new InfoAdapter(getContext());
        rcv.setAdapter(adapter);
    }
    class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ItemHolder>{
        private Context context;

        public InfoAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.fragment_cocknumber_info_item,parent,false);
            ItemHolder holder = new ItemHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.tv.setText(strList.get(position));
        }

        @Override
        public int getItemCount() {
            return strList.size();
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public ItemHolder(View itemView) {
                super(itemView);
                tv = (TextView)itemView.findViewById(R.id.tv_number);
            }
        }
    }
}
