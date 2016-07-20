package com.dalbo.jajanon.Frag.f_lapak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.LapakData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class lokasi extends Fragment implements OnMapReadyCallback {
    MapView mapv;
    GoogleMap gmap;
    LatLng ll;
    LapakData data;
    public lokasi(LapakData d) {
        this.data = d;
    }

    // Pembuatan view berisi map yang digunakan untuk menampilkan lokasi
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_lapak_lokasi, container, false);
        mapv = (MapView) v.findViewById(R.id.mapv);
        mapv.onCreate(savedInstanceState);
        mapv.onResume();
        mapv.getMapAsync(this);
        return v;
    }
    //listener ketika map telah sedia untuk ditampilkan
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gmap = googleMap;
        gmap.addMarker(new MarkerOptions().position(data.getDataLapak().getLl()));
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(data.getDataLapak().getLl(),15f));
        gmap.setMyLocationEnabled(true);
    }
}
