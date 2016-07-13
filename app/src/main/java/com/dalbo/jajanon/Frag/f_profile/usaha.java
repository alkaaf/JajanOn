package com.dalbo.jajanon.Frag.f_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.HomeListRowView;
import com.dalbo.jajanon.Entity.HomeList;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class usaha extends Fragment{
    ListView lv;

    // set adapter pada listview dan tampilkan view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_tab_list,container,false);
        lv = (ListView)v.findViewById(R.id.list_usaha);
        lv.setAdapter(new HomeListRowView(getContext(), HomeList.getDummy()));
        return v;
    }
}
