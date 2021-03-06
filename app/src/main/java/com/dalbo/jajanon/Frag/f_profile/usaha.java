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
import android.widget.Button;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.KelolaLapak;
import com.dalbo.jajanon.LapakActivity;
import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.SvcUser;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class usaha extends Fragment implements ListView.OnItemClickListener {
    SvcUser data;
    ListView lv;
    Activity act;
    Context c;
    Button addUsaha;

    public usaha() {
    }

    public usaha(SvcUser d) {
        this.data = d;
    }

    // set adapter pada listview dan tampilkan view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_tab_list, container, false);
        lv = (ListView) v.findViewById(R.id.list_usaha);
        c = getContext();
        act = getActivity();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                data.connectLapakKu();
//                act.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        lv.setAdapter(new KelolaLapak(c, act, data.getLapakKu()));
//                    }
//                });
//            }
//        }).start();
        lv.setAdapter(new KelolaLapak(getContext(), getActivity(), data.getLapakKu()));
        lv.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getContext(), LapakActivity.class);
        i.putExtra("lid", data.getLapakKu().get(position).getId());
        startActivity(i);
    }
}
