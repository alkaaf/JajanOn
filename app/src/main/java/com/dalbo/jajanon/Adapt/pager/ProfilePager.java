package com.dalbo.jajanon.Adapt.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dalbo.jajanon.Frag.f_home.terbaru;
import com.dalbo.jajanon.Frag.f_home.terdekat;
import com.dalbo.jajanon.Frag.f_home.top;
import com.dalbo.jajanon.Frag.f_profile.langganan;
import com.dalbo.jajanon.Frag.f_profile.overview;
import com.dalbo.jajanon.Frag.f_profile.usaha;

/**
 * Created by alkaaf on 7/7/2016.
 */


public class ProfilePager extends FragmentPagerAdapter {
    ViewPager vp;
    public ProfilePager(FragmentManager fm) {
        super(fm);
    }

    public ProfilePager(FragmentManager fm, ViewPager vp) {
        super(fm);
        this.vp = vp;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new overview(vp);
        } else if (position == 1) {
            return new langganan();
        } else if (position == 2) {
            return new usaha();
        } else return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "OVERVIEW";
        } else if (position == 1) {
            return "LANGGANAN";
        } else if (position == 2) {
            return "USAHA";
        } else return null;
    }
}
