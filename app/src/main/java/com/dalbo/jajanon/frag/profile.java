package com.dalbo.jajanon.Frag;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;


import com.dalbo.jajanon.Adapt.ProfilePager;
import com.dalbo.jajanon.MapActivity;
import com.dalbo.jajanon.Frag.profil_tab.*;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 6/23/2016.
 */
public class profile extends Fragment implements View.OnClickListener {
    Activity a;
    Context c;
    ImageView b_bukaPeta;
    ViewPager vp;
    public profile() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frag_profile, container, false);
        c = getActivity();
        a = getActivity();

        b_bukaPeta = (ImageView) v.findViewById(R.id.b_bukaPeta);
        b_bukaPeta.setOnClickListener(this);
        vp = (ViewPager)v.findViewById(R.id.content_profile);
        vp.setAdapter(new ProfilePager(getChildFragmentManager()));

        ((Toolbar)a.findViewById(R.id.toolbar)).setTitle("Profil");
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == b_bukaPeta){
            Intent i = new Intent(c, MapActivity.class);
//            startActivity(new Intent(this,MapActivity.class));
            startActivity(i);
        }
    }
}

