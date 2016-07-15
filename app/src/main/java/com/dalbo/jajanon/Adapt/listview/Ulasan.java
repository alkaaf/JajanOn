package com.dalbo.jajanon.Adapt.listview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dalbo.jajanon.CustomClass.Rounder;
import com.dalbo.jajanon.Entity.DataUlasan;
import com.dalbo.jajanon.R;

import java.util.ArrayList;

/**
 * Created by alkaaf on 7/10/2016.
 */
public class Ulasan extends ArrayAdapter<DataUlasan>{
    ArrayList<DataUlasan> data;
    Context c;
    public Ulasan(Context context, ArrayList<DataUlasan> objects) {
        super(context, R.layout.n_lapak_ulasan_listview, objects);
        this.data = objects;
        this.c = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.n_lapak_ulasan_listview,parent,false);

        // declare views
        TextView nama, tanggal, ulasan;
        ImageView avatar;
        RatingBar rb;
        nama = (TextView)v.findViewById(R.id.nama);
        tanggal = (TextView)v.findViewById(R.id.tanggal);
        rb = (RatingBar)v.findViewById(R.id.ulasan_rating);
        ulasan = (TextView)v.findViewById(R.id.ulasan);
        avatar = (ImageView)v.findViewById(R.id.img_avatar);

        // setting view
        nama.setText(data.get(position).getNama());
        tanggal.setText(data.get(position).getTanggal());
        ulasan.setText(data.get(position).getUlasan());
        rb.setRating(data.get(position).getRating());
        avatar.setImageDrawable(new Rounder(BitmapFactory.decodeResource(c.getResources(),R.drawable.dummy_avatar)));
        return v;
    }
}
