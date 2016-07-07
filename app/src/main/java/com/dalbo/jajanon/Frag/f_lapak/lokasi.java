package com.dalbo.jajanon.Frag.f_lapak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalbo.jajanon.R;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class lokasi extends Fragment {
    public lokasi() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_lapak_lokasi,container,false);

//        SupportMapFragment mapFragment = getChildFragmentManager().findFragmentById();
        return v;
    }
}
