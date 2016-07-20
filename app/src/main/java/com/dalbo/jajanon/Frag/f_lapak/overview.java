package com.dalbo.jajanon.Frag.f_lapak;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dalbo.jajanon.ProfileActivity;
import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.LapakData;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class overview extends Fragment implements View.OnClickListener{
    LapakData data;
    public overview(LapakData d) {
        this.data = d;
    }
    RatingBar ratingBar;
    TextView ratingVal, totalPelanggan, totalUlasan, penjual, alamat, jambuka;
    Button showProfile, addUlasan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_lapak_overview, container, false);
        ratingVal = (TextView)v.findViewById(R.id.angkarate);
        ratingBar = (RatingBar)v.findViewById(R.id.rating);
        totalUlasan = (TextView)v.findViewById(R.id.total_ulasan);
        totalPelanggan = (TextView)v.findViewById(R.id.total_pelanggan);
        penjual = (TextView)v.findViewById(R.id.penjual);
        alamat = (TextView)v.findViewById(R.id.alamat);
        jambuka = (TextView)v.findViewById(R.id.jambuka);
        showProfile = (Button)v.findViewById(R.id.show_profile);
        addUlasan = (Button)v.findViewById(R.id.add_ulasan);
        showProfile.setOnClickListener(this);
        addUlasan.setOnClickListener(this);
        ratingBar.setRating(data.getDataLapak().getRating());
        totalUlasan.setText(data.getUlasan().size() + " Ulasan");
        totalPelanggan.setText(data.getTotalPelanggan()+" Pelanggan");
        penjual.setText(data.getDataLapak().getNama());
        alamat.setText(data.getDataLapak().getAlamat());
        ratingVal.setText(data.getDataLapak().getRating()+"/5");
        jambuka.setText(data.getDataLapak().getBuka() + " - " + data.getDataLapak().getTutup());
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == showProfile){
            Intent i = new Intent(getContext(), ProfileActivity.class);
            i.putExtra("uid",data.getDataLapak().getId_user());
            startActivity(i);
        } else  if(v == addUlasan){

        }
    }
}
