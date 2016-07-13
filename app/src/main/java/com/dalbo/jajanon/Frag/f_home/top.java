package com.dalbo.jajanon.Frag.f_home;

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

import com.dalbo.jajanon.Adapt.listview.HomeListRowView;
import com.dalbo.jajanon.Entity.HomeList;
import com.dalbo.jajanon.LapakActivity;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class top extends Fragment implements ListView.OnItemClickListener{
    Context c;
    ListView lv;
    public top() {
    }
    // pembuatan view untuk fragment berisi setAdapter terhadap listview
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_home_tab,container,false);
        lv = (ListView)v.findViewById(R.id.home_lv);
        lv.setAdapter(new HomeListRowView(getContext(), HomeList.getDummy()));
        lv.setOnItemClickListener(this);
        return v;
    }
    // listener ketika terdapat item diclick
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), LapakActivity.class);
        startActivity(intent);
    }
}
