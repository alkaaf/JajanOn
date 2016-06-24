package com.dalbo.jajanon;

import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alkaaf on 6/24/2016.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap gmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        LatLng ll = new LatLng(9,9);
        gmap.addMarker(new MarkerOptions().position(ll).title("Testing"));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll,18));
    }
}
