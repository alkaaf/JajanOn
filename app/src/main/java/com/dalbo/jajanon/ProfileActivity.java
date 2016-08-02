package com.dalbo.jajanon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalbo.jajanon.Adapt.pager.ProfilePager;
import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.CustomClass.Rounder;
import com.dalbo.jajanon.Service.SvcUser;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class ProfileActivity extends AppCompatActivity {
    ImageView avatar;
    TextView nama, email;
    ViewPager vp;
    SvcUser d;
    int uid;
    Context c;
    Activity act;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_profile);
        Pref.init(this);
//        uid = Pref.getUid();
        uid = getIntent().getExtras().getInt("uid",0);
        c = this;
        act = this;
        avatar = (ImageView)findViewById(R.id.img_avatar);
        nama = (TextView)findViewById(R.id.nama);
        email = (TextView)findViewById(R.id.email);
        vp = (ViewPager)findViewById(R.id.profile_content);
        d = new SvcUser(uid,getString(R.string.svc),c,act);
        // setting avatar image, name, email
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading = new ProgressDialog(c);
                        loading.setMessage("Tunggu sebentar...");
                        loading.show();
                    }
                });
                d.connect();
                final Bitmap avatarBm = d.getBitmapAvatar();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nama.setText(d.getUsername());
                        email.setText(d.getEmail());
                        avatar.setImageDrawable(new Rounder(avatarBm));
                        vp.setAdapter(new ProfilePager(getSupportFragmentManager(), d,vp));
                        vp.setCurrentItem(getIntent().getExtras().getInt("tab",0));
                        loading.dismiss();
                    }
                });
            }
        }).start();
    }
}