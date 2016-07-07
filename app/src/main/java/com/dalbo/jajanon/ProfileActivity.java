package com.dalbo.jajanon;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dalbo.jajanon.Adapt.pager.ProfilePager;
import com.dalbo.jajanon.CustomClass.Rounder;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    ImageView avatar;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_profile);
        avatar = (ImageView)findViewById(R.id.img_avatar);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.dummy_avatar);
        avatar.setImageDrawable(new Rounder(bm));
        vp = (ViewPager)findViewById(R.id.profile_content);
        vp.setAdapter(new ProfilePager(getSupportFragmentManager(),vp));
    }
}
