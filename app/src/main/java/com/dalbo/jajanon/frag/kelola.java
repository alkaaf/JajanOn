package com.dalbo.jajanon.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 6/23/2016.
 */
public class kelola extends Fragment {
    public kelola() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_kelola, container, false);
    }
}
