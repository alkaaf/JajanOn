package com.dalbo.jajanon.frag;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.dalbo.jajanon.CustomClass.FileChooser;
import com.dalbo.jajanon.R;

import java.io.File;

/**
 * Created by alkaaf on 6/23/2016.
 */
public class kelola extends Fragment {
    Context c;
    Activity a;
    public kelola() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frag_kelola, container, false);
        c = getActivity();
        a = getActivity();
        ((Toolbar)a.findViewById(R.id.toolbar)).setTitle("Kelola Dagangan");
        ((Button)v.findViewById(R.id.button_daftar)).setOnClickListener(new View.OnClickListener() {
            Button b_avatarUpload;
            Button b_coverUpload;
            EditText path_avatar, path_cover;

            @Override
            public void onClick(View v) {
//                Toast.makeText(c,"Daftar hahaha",Toast.LENGTH_SHORT).show();
                Dialog register = new Dialog(c);
                final FileChooser fc = new FileChooser(a);
                register.setContentView(R.layout.popup_daftardagang);
                path_avatar = (EditText)register.findViewById(R.id.path_avatar);
                path_cover = (EditText)register.findViewById(R.id.path_cover);
                b_avatarUpload = (Button)register.findViewById(R.id.b_avatarUpload);
                b_avatarUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fc.showDialog();
                        setPath(path_avatar,fc);
                    }
                });
                b_coverUpload = (Button)register.findViewById(R.id.b_coverUpload);
                b_coverUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fc.showDialog();
                        setPath(path_cover,fc);
                    }
                });
                register.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                register.show();
            }
            public void setPath(final View v, FileChooser fcs){
                fcs.setFileListener(new FileChooser.FileSelectedListener() {
                    @Override
                    public void fileSelected(File file) {
                        ((EditText)v).setText(file.getAbsolutePath());
                    }
                });
            }
        });
        return v;
    }
}
