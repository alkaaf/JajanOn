package com.dalbo.jajanon.Adapt.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dalbo.jajanon.Frag.f_profile.langganan;
import com.dalbo.jajanon.Frag.f_profile.overview;
import com.dalbo.jajanon.Frag.f_profile.usaha;
import com.dalbo.jajanon.Service.UserData;

/**
 * Created by alkaaf on 7/7/2016.
 */


public class ProfilePager extends FragmentPagerAdapter {
    ViewPager vp;
    UserData data;
    public ProfilePager(FragmentManager fm, UserData d) {
        super(fm);
        this.data = d;
    }

    public ProfilePager(FragmentManager fm, UserData d, ViewPager vp) {
        super(fm);
        this.vp = vp;
        this.data = d;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new overview(data);
        } else if (position == 1) {
            return new langganan(data);
        } else if (position == 2) {
            return new usaha(data);
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
