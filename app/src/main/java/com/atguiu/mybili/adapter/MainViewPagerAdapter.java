package com.atguiu.mybili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguiu.mybili.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;
    private String[] titles = new String[]{"直播", "推荐","追番","分区","发现"};

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments  = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
