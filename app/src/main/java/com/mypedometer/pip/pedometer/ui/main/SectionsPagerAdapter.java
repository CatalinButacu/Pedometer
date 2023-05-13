package com.mypedometer.pip.pedometer.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mypedometer.pip.pedometer.MainActivity;
import com.mypedometer.pip.pedometer.R;
import com.mypedometer.pip.pedometer.ui.fragments.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    View fragmentContainer;
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    @LayoutRes
    private static final int[] LAYOUT_IDS = new int[]{R.layout.status_layout, R.layout.challenge_layout, R.layout.profile_layout};

    private final Context mContext;

    public static final List<Fragment> FRAGMENTS = new ArrayList<Fragment>(Arrays.asList(
            new StatusFragment(),
            new ChallengeFragment(),
            new LoginFragment()
    ))
    {
        @Override
        public Fragment set(int index, Fragment element) {
            Fragment fragment = super.set(index, element);
            SectionsPagerAdapter adapter = (SectionsPagerAdapter) MainActivity.binding.viewPager.getAdapter();
            adapter.notifyDataSetChanged();
            return fragment;
        }
    };

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return FRAGMENTS.get(position);
        }
        catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid position: " + position);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        catch (Exception e) {
            try {
                throw new Exception("Invalid position: " + position);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
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
