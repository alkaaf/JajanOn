package com.dalbo.jajanon;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dalbo.jajanon.Adapt.pager.HomePager;
import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Dialg.login;
import com.dalbo.jajanon.Dialg.register;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager home_content;
    Menu mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // deklarasi navigation menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mainMenu = navigationView.getMenu();
        Pref.init(this);

        // deklarasi variabel untuk toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // deklarasi viewpager kedalam variabel
        home_content = (ViewPager) findViewById(R.id.home_content);

        // masukkan adapter kedalam viewpager
        home_content.setAdapter(new HomePager(getSupportFragmentManager(), this, this));

        toolbar.setTitle("JajanOn");
        setSupportActionBar(toolbar);
        // deklarasi navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (Pref.getUid() > 0) {
                    mainMenu.findItem(R.id.nav_profil).setVisible(true);
                    mainMenu.findItem(R.id.nav_subscribelist).setVisible(true);
                    mainMenu.findItem(R.id.nav_tambahusaha).setVisible(true);
                    mainMenu.findItem(R.id.nav_logout).setVisible(true);
                    mainMenu.findItem(R.id.nav_login).setVisible(false);
                    mainMenu.findItem(R.id.nav_register).setVisible(false);
                } else if (Pref.getUid() == 0) {
                    mainMenu.findItem(R.id.nav_profil).setVisible(false);
                    mainMenu.findItem(R.id.nav_subscribelist).setVisible(false);
                    mainMenu.findItem(R.id.nav_tambahusaha).setVisible(false);
                    mainMenu.findItem(R.id.nav_logout).setVisible(false);
                    mainMenu.findItem(R.id.nav_login).setVisible(true);
                    mainMenu.findItem(R.id.nav_register).setVisible(true);
                }
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    // override ketika tombol back android ditekan
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // pemberian flag ketika item option dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // aksi ketika item dalam navigation drawer dipilih
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;

        if (id == R.id.nav_profil) {
            i = new Intent(this, ProfileActivity.class);
            i.putExtra("uid",Pref.getUid());
            i.putExtra("tab", 0);
            startActivity(i);
        } else if (id == R.id.nav_login) {
            Dialog d = new login(this, this);
            d.setTitle("Login");
            d.getWindow().setTitleColor(R.color.white);
            d.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
            d.setCancelable(true);
            d.show();
        } else if (id == R.id.nav_register){
            Dialog d = new register(this,this);
            d.setTitle("Register");
            d.getWindow().setTitleColor(R.color.white);
            d.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
            d.setCancelable(true);
            d.show();
        } else if (id == R.id.nav_logout) {
            Pref.putUid(0);
        } else if (id == R.id.nav_subscribelist) {
            i = new Intent(this, ProfileActivity.class);
            i.putExtra("tab", 1);
            startActivity(i);
        } else if (id == R.id.nav_tambahusaha) {
            i = new Intent(this, ProfileActivity.class);
            i.putExtra("tab", 2);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
