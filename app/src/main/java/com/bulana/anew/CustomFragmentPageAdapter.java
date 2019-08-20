package com.bulana.anew;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 2;

    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new BitcoinFragment();
            case 1:
                return new TopBusinessHeadLinesFragment();
            case 2:
//                return new ReferenceFragment();
//            case 3:
//                return new SummaryFragment();
//            case 4:
//                return new CurriculumVitaeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Bitcoin";
            case 1:
                return "Head Lines";
//            case 2:
//                return "References";
//            case 3:
//                return "Let's Chat";
        }
        return null;
    }
}
