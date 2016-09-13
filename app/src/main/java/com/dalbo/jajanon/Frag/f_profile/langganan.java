package com.dalbo.jajanon.Frag.f_profile;

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
import com.dalbo.jajanon.Service.SvcUser;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class langganan extends Fragment implements ListView.OnItemClickListener{
    ListView lv;
    SvcUser data;
    Context c;
    Activity act;
    public langganan() {
    }

    public langganan(SvcUser d) {
        this.data = d;
    }

    @Nullable
    @Override
    // inflate view set adapter
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_tab_list,container,false);
        lv = (ListView)v.findViewById(R.id.list_usaha);
        c = getContext();
        act = getActivity();
        // fetch langgan data
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                data.connectLapakLanggan();
//                act.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        lv.setAdapter(new Default(c,act,data.getLapakLanggan()));
//                    }
//                });
//            }
//        }).start();
        lv.setAdapter(new Default(getContext(),getActivity(), data.getLapakLanggan()));
        lv.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getContext(), LapakActivity.class);
        i.putExtra("lid",data.getLapakLanggan().get(position).getId());
        startActivity(i);
    }
}
