package com.dalbo.jajanon.Frag.f_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class overview extends Fragment implements View.OnClickListener{
    Button showusaha, showlangganan;
    ViewPager vp;

    public overview(ViewPager vp) {
        this.vp = vp;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_overview,container,false);
        showusaha = (Button)v.findViewById(R.id.show_usaha);
        showlangganan = (Button)v.findViewById(R.id.show_langganan);
        showusaha.setOnClickListener(this);
        showlangganan.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == showusaha){
            vp.setCurrentItem(2);
        }else if(v == showlangganan){
            vp.setCurrentItem(1);
        }
    }
}
