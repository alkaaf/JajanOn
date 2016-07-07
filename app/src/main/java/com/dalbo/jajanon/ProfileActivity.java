package com.dalbo.jajanon;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dalbo.jajanon.Adapt.pager.ProfilePager;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class ProfileActivity extends AppCompatActivity {

    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_profile);
        vp = (ViewPager)findViewById(R.id.profile_content);
        vp.setAdapter(new ProfilePager(getSupportFragmentManager(),vp));
    }
}
