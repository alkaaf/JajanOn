package com.dalbo.jajanon.Adapt.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dalbo.jajanon.Frag.f_lapak.lokasi;
import com.dalbo.jajanon.Frag.f_lapak.overview;
import com.dalbo.jajanon.Frag.f_lapak.ulasan;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class LapakPager extends FragmentPagerAdapter{
    // konstruktor untuk mengisi fragmentmanager pada parent
    public LapakPager(FragmentManager fm) {
        super(fm);
    }

    // Pengembalian fragment sebagai view di viewpager
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new lokasi();
        } else if (position == 1) {
            return new overview();
        } else if (position == 2) {
            return new ulasan();
        } else return null;
    }
    // Mengembalikan jumlah tab dalam viewpager
    @Override
    public int getCount() {
        return 3;
    }

    // pemBerian judul pada tab
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "LOKASI";
        } else if (position == 1) {
            return "OVERVIEW";
        } else if (position == 2) {
            return "ULASAN";
        } else return null;
    }
}
