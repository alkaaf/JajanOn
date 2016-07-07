package com.dalbo.jajanon.Adapt.pager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.dalbo.jajanon.Frag.f_home.terbaru;
import com.dalbo.jajanon.Frag.f_home.terdekat;
import com.dalbo.jajanon.Frag.f_home.top;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class HomePager extends FragmentPagerAdapter {
    int n;
    Context c;
    Activity a;
    public HomePager(FragmentManager fm, Context c, Activity a) {
        super(fm);
        this.n = 3;
        this.c = c;
        this.a = a;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new terdekat();
        } else if (position == 1) {
            return new terbaru();
        } else if (position == 2) {
            return new top();
        } else return null;
    }

    @Override
    public int getCount() {
        return n;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "TERDEKAT";
        } else if (position == 1) {
            return "TERBARU";
        } else if (position == 2) {
            return "TOP";
        } else return null;
    }

}
