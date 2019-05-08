package com.biotag.gamecock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biotag.gamecock.R;
import com.biotag.gamecock.adapter.CockinfoAdapter;

import java.util.ArrayList;

/**
 * Created by Lxh on 2017/8/4.
 */

public class MainFragment extends Fragment {

    private static final int GETDATA_SUCCESS = 1;
    private static final int GETDATA_FAIL = 0;
//    private ChanticleerBean cb;
    private ArrayList<String> mDatas = new ArrayList<>();
    private static final String ARG_TITLE = "title";
    private String mTitle;
    private RecyclerView rcv;
    private CockinfoAdapter mCockinfoAdapter;
    private String[] cockInfoType = new String[]{
            "芯片号", "斗鸡编号", "品种", "出生日期",
            "产地"
    };
    private ArrayList<String> cockInfoTypeList = new ArrayList<>();
    public static MainFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE,title);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case GETDATA_SUCCESS:
//
//                    break;
//                case GETDATA_FAIL:
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cocksecond_info,container,false);
        rcv = (RecyclerView)v.findViewById(R.id.cocksecondrcv);
        initdata();
//        initdata();
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCockinfoAdapter = new CockinfoAdapter(getContext(),mDatas);
        rcv.setAdapter(mCockinfoAdapter);
        return v;
    }

    private void initdata() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(mTitle + (char) i);
        }
    }

//    private void inittypes() {
//        for (int i = 0; i < cockInfoType.length; i++) {
//            cockInfoTypeList.add(cockInfoType[i]);
//        }
//    }

//    private void initdata() {
//        final String requestUrl = Constants.COCK_INFO_URL;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                 cb = OkhttpPlusUtil.getInstance().get(requestUrl, ChanticleerBean.class);
//                if(cb!=null){
//                    handler.sendEmptyMessage(GETDATA_SUCCESS);
//                }
//            }
//        }).start();
//    }
}
