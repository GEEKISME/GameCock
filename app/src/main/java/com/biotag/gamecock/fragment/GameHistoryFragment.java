package com.biotag.gamecock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biotag.gamecock.R;

/**
 * Created by Lxh on 2017/8/8.
 */

public class GameHistoryFragment extends Fragment {

    private String game;
    private static final String ARG = "Game";
    public static GameHistoryFragment newInstance(String game) {
        Bundle args = new Bundle();
        args.putString(ARG,game);
        GameHistoryFragment fragment = new GameHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        game = bundle.getString(ARG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cockthird_info,container,false);
        return v;
    }

}
