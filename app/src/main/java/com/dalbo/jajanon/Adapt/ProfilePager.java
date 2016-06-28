package com.dalbo.jajanon.Adapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dalbo.jajanon.Frag.home;
import com.dalbo.jajanon.Frag.profil_tab.*;

/**
 * Created by alkaaf on 6/28/2016.
 */
public class ProfilePager extends FragmentPagerAdapter {
    public ProfilePager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new profil_info();
        } else if (position == 1) {
            return new profil_usaha();
        } else if (position == 2) {
            return new profil_komentar();
        } else return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Info";
        } else if (position == 1) {
            return "Usaha";
        } else if (position == 2) {
            return "Komentar";
        } else return null;
    }
}
