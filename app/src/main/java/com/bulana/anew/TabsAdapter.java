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
                BitcoinNewsFragment home = new BitcoinNewsFragment();
                return home;
            case 1:
                AboutFragment about = new AboutFragment();
                return about;
            case 2:
                ContactFragment contact = new ContactFragment();
                return contact;
            default:
                return null;
        }
    }
}
