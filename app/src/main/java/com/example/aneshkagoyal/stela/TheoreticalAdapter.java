package com.example.aneshkagoyal.stela;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class TheoreticalAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public TheoreticalAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    private String[] tabTitles = new String[] {"About", "Interfacing Diagram", "Program Code" };
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AboutFragment();
        } else if (position == 1) {
            return new DiagramFragment();
        } else {
            return new CodeFragment();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
