package com.mypedometer.pip.pedometer.ui.main;

import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mypedometer.pip.pedometer.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    @LayoutRes
    private static final int[] LAYOUT_IDS = new int[]{R.layout.status_layout, R.layout.challenge_layout, R.layout.profile_layout};

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new StatusFragment();
                break;
            case 1:
                fragment = new ChallengeFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
                break;
        }
        return fragment;
        //return PlaceholderFragment.newInstance(LAYOUT_IDS[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return LAYOUT_IDS.length; // Show number of total pages
    }
}
