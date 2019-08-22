package com.bulana.anew;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumberOfTabs;
    public TabsAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                BitcoinNewsFragment bitcoinNews = new BitcoinNewsFragment();
                return bitcoinNews;
            case 1:
                BusinessNewsFragment businessNews = new BusinessNewsFragment();
                return businessNews;
            case 2:
                SportsFragment sports = new SportsFragment();
                return sports;
            default:
                return null;

        }
    }
}
