package com.dalbo.jajanon.Frag.f_lapak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.Ulasan;
import com.dalbo.jajanon.Entity.DataUlasan;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class ulasan extends Fragment{
    ListView lv;
    Button tambah_ulasan;

    public ulasan() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_lapak_ulasan,container,false);
        lv = (ListView)v.findViewById(R.id.ulasan_list);
        tambah_ulasan = (Button)v.findViewById(R.id.tambah_ulasan);
        lv.setAdapter(new Ulasan(getContext(), DataUlasan.getDummy()));
        return v;
    }
}
