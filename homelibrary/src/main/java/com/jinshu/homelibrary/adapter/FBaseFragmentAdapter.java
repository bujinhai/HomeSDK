package com.jinshu.homelibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 该类内的每一个生成的 Fragment 都将保存在内存之中，
 * 因此适用于那些相对静态的页，数量也比较少的那种；
 * 如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，
 * 应该使用FragmentStatePagerAdapter。
 */
public class FBaseFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> mTitles;

    public FBaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public FBaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
        setFragments(fm, fragmentList, mTitles);
    }

    //刷新fragment
    public void setFragments(FragmentManager fm, List<Fragment> fragments, List<String> mTitles) {
        this.mTitles = mTitles;
        if (this.fragmentList != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragmentList) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
        this.fragmentList = fragments;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return !isNullOrEmpty(mTitles) ? mTitles.get(position) : "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }

}
