package com.biotag.gamecock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.biotag.gamecock.R;
import com.biotag.gamecock.adapter.FragmentViewPagerAdapter;
import com.biotag.gamecock.fragment.MainFragment;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class CockInfoActivity extends AppCompatActivity {

    private String cockId;
    private ViewPager viewpager;
    private CoordinatorTabLayout coordinatortabLayout;
    private int[] mImageArrsy,mColorArray;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitle = {"基本信息","比赛信息"};
    private FragmentViewPagerAdapter  mFragmentViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cock_info);
        cockId = getIntent().getStringExtra("cockid");
        initView();
        mImageArrsy = new int[]{
                R.mipmap.material_flat,
                R.mipmap.material_flat
//                R.mipmap.material_flat,
//                R.mipmap.material_flat
        };
        mColorArray = new int[]{
//                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light
//                android.R.color.holo_green_light
        };
        initfragments();
        initviewpager();
        coordinatortabLayout.setTitle(cockId)
                .setBackEnable(true)
                .setContentScrimColorArray(mColorArray)
                .setImageArray(mImageArrsy)
                .setupWithViewPager(viewpager);
    }


    private void initviewpager() {
        viewpager.setOffscreenPageLimit(4);
        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),mFragments,mTitle);
        viewpager.setAdapter(mFragmentViewPagerAdapter);
    }

    private void initfragments() {
        for (int i = 0; i < mTitle.length; i++) {
            mFragments.add(MainFragment.newInstance(mTitle[i]));
        }
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        coordinatortabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortabLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
