package com.dalbo.jajanon.Frag.f_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.SvcUser;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class overview extends Fragment{

    SvcUser data;

    public overview() {
    }

    // set viewpager untuk tombol
    public overview(SvcUser d) {
        this.data = d;
    }

    // inflate view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_overview,container,false);
        TextView jml_lapak, jml_langgan, terdaftar;
        jml_lapak = (TextView)v.findViewById(R.id.jml_lapak);
        jml_langgan = (TextView)v.findViewById(R.id.jml_langganan);
        terdaftar = (TextView)v.findViewById(R.id.tanggal_daftar);
        jml_lapak.setText(data.getJumlahLapak()+" Lapak");
        jml_langgan.setText(data.getJumlahLanggan()+" Lapak");
        terdaftar.setText(data.getTanggalDaftar());
        return v;
    }
}
