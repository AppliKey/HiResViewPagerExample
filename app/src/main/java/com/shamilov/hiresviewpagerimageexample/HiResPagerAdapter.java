package com.shamilov.hiresviewpagerimageexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.io.File;
import java.util.List;


public class HiResPagerAdapter extends FragmentStatePagerAdapter {

    private List<File> mImages;

    HiResPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setImages(List<File> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mImages == null) {
            return 0;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(mImages.get(position % mImages.size()));
    }
}
