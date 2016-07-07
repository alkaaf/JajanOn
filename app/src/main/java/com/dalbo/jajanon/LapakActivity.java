package com.dalbo.jajanon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dalbo.jajanon.Adapt.pager.LapakPager;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class LapakActivity extends AppCompatActivity {

    ViewPager vp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_lapak);
        vp =(ViewPager)findViewById(R.id.lapak_content);
        vp.setAdapter(new LapakPager(getSupportFragmentManager()));
    }
}
