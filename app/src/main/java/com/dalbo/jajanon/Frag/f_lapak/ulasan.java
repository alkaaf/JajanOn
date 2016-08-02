package com.dalbo.jajanon.Frag.f_lapak;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.Ulasan;
import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Dialg.addUlasan;
import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.SvcLapak;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class ulasan extends Fragment implements View.OnClickListener{
    ListView lv;
    Button tambah_ulasan;
    SvcLapak data;
    public ulasan(SvcLapak d) {
        this.data = d;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_lapak_ulasan,container,false);
        lv = (ListView)v.findViewById(R.id.ulasan_list);
        tambah_ulasan = (Button)v.findViewById(R.id.addUlasan);
        lv.setAdapter(new Ulasan(getContext(),data.getUlasan()));
        tambah_ulasan.setOnClickListener(this);
//        lv.setAdapter(new Ulasan(getContext(), DataUlasan.getDummy()));
        Pref.init(getContext());
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == tambah_ulasan){
            Dialog d = new addUlasan(data.getDataLapak().getId(),getContext(),getActivity());
            d.show();
        }
    }
}
