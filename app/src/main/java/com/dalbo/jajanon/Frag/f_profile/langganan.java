package com.dalbo.jajanon.Frag.f_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dalbo.jajanon.Adapt.listview.Default;
import com.dalbo.jajanon.R;
import com.dalbo.jajanon.Service.UserData;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class langganan extends Fragment{
    ListView lv;
    UserData data;

    public langganan() {
    }

    public langganan(UserData d) {
        this.data = d;
    }

    @Nullable
    @Override
    // inflate view set adapter
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.n_profile_tab_list,container,false);
        lv = (ListView)v.findViewById(R.id.list_usaha);
        lv.setAdapter(new Default(getContext(), data.getLapakLanggan()));
        return v;
    }
}
