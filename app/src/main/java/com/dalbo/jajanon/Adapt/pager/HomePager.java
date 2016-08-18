package com.dalbo.jajanon.Adapt.pager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dalbo.jajanon.Frag.f_home.terbaru;
import com.dalbo.jajanon.Frag.f_home.terdekat;
import com.dalbo.jajanon.Frag.f_home.top;
import com.dalbo.jajanon.Service.SvcAllLapak;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class HomePager extends FragmentPagerAdapter {
    int n;
    Context c;
    Activity a;
    SvcAllLapak data;
    // konstruktor untuk mengisi fragmentmanager pada parent
    public HomePager(FragmentManager fm, SvcAllLapak svc) {
        super(fm);
        this.data = svc;
        this.n = 3;
    }

    // Pengembalian fragment sebagai view di viewpager
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new terdekat(data);
        } else if (position == 1) {
            return new terbaru(data);
        } else if (position == 2) {
            return new top(data);
        } else return null;
    }

    // Mengembalikan jumlah tab dalam viewpager
    @Override
    public int getCount() {
        return n;
    }

    // pemBerian judul pada tab
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
