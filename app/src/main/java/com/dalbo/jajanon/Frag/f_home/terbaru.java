package com.dalbo.jajanon.Frag.f_home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.Default;
import com.dalbo.jajanon.LapakActivity;
import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.SvcAllLapak;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class terbaru extends Fragment implements ListView.OnItemClickListener{
//    Context c;
    SvcAllLapak data;
    ListView lv;
    Activity act;
    Context c;
    //blank constructor fragment
    public terbaru(SvcAllLapak data){
        this.data = data;
    }
    // pembuatan view dengan cara inflate layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_home_tab,container,false);
        act = getActivity();
        c = getContext();
        lv = (ListView)v.findViewById(R.id.home_lv);
        lv.setOnItemClickListener(this);
        lv.setAdapter(new Default(c,act,data.getListLapak()));
        return v;
    }

    // listener ketika terdapat item diclick
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), LapakActivity.class);
        intent.putExtra("lid",data.getListLapak().get(position).getId());
        startActivity(intent);
    }
}
