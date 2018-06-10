package com.bnk.myedx;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SimplePagerAdapter extends FragmentPagerAdapter {
    /** Context of the app */
    private Context mContext;



    public SimplePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext=context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new CoursesFragment();
            case 1:
                return  new DiscoverFragment();
            default:
                return null;
        }
    }
    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return  mContext.getString(R.string.courses);
            case 1:
                return  mContext.getString(R.string.discovery);

            default:
                return null;
        }
    }
}
