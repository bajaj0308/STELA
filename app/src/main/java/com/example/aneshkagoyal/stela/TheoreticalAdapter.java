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

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AboutFragment();
        } else if (position == 1) {
            return new DiagramFragment();
        } else if (position == 2) {
            return new CodeFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "About";
        } else if (position == 1) {
            return "Interfacing Diagram";
        } else if (position == 2) {
            return "Program Code";
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
