package com.dalbo.jajanon.Frag;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 6/23/2016.
 */
public class home extends Fragment {
    Activity a;
    Context c;
    EditText text_search;
    public home() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_home, container, false);
        c = getActivity();
        a = getActivity();
        text_search = (EditText)v.findViewById(R.id.text_cari);
        text_search.clearFocus();
        ((Toolbar)a.findViewById(R.id.toolbar)).setTitle("JajanOn");
        return v;
    }
}
