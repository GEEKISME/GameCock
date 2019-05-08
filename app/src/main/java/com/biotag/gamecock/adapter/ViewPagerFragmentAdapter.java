package com.biotag.gamecock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab;
    private ArrayList<SupportFragment> mFragments ;


    public ViewPagerFragmentAdapter(FragmentManager fm, String[] titles, ArrayList<SupportFragment> supportFragments) {
        super(fm);
        mFragments = supportFragments;
        mTab = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0:mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }

    public void addItem(SupportFragment supportFragment){
        mFragments.add(supportFragment);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    public void addItemAll(ArrayList<SupportFragment> supportFragments){
        mFragments.addAll(getCount(),supportFragments);
        notifyDataSetChanged();
    }




}
