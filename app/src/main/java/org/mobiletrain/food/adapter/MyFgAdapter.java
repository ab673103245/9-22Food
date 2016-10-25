package org.mobiletrain.food.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.mobiletrain.food.bean.ClassfyBean;

import java.util.List;

/**
 * Created by 王松 on 2016/8/1.
 */
public class MyFgAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<ClassfyBean> title;

    public MyFgAdapter(FragmentManager fm, List<Fragment> fragments, List<ClassfyBean> title) {
        super(fm);
        this.fragments = fragments;
        this.title = title;
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
        return title.get(position).getName();
    }
}
