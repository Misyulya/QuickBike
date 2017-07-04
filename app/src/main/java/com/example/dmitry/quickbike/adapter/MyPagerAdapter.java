package com.example.dmitry.quickbike.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dmitry.quickbike.fragment.OnlyMapFragment;
import com.example.dmitry.quickbike.fragment.TemporaryFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String mTabTitles[] = new String[]{"Map", "Personal", "Help"};
    private Context mContext;
    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return OnlyMapFragment.newInstance(1, "Map Fragment");
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return TemporaryFragment.newInstance(2, "It will be personal account");
            case 2: // Fragment # 1 - This will show SecondFragment
                return TemporaryFragment.newInstance(3, "It will be something else...");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
