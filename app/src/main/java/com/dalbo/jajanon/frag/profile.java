package com.dalbo.jajanon.frag;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dalbo.jajanon.MapActivity;
import com.dalbo.jajanon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.Map;

/**
 * Created by alkaaf on 6/23/2016.
 */
public class profile extends Fragment implements View.OnClickListener {
    Activity a;
    Context c;
    Button b_bukaPeta;
    public profile() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frag_profile, container, false);
        c = getActivity();
        a = getActivity();
        b_bukaPeta = (Button) v.findViewById(R.id.b_bukaPeta);
        b_bukaPeta.setOnClickListener(this);
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

