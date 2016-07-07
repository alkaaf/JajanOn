package com.dalbo.jajanon.Adapt.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalbo.jajanon.CustomClass.Rounder;
import com.dalbo.jajanon.Entity.HomeList;
import com.dalbo.jajanon.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class HomeListRowView extends ArrayAdapter<HomeList> {

    ArrayList<HomeList> data;
    Context c;
    LayoutInflater li;
    public HomeListRowView(Context context, ArrayList<HomeList> objects) {
        super(context, R.layout.n_home_listrowview, objects);
        this.data = objects;
        this.c = context;
        this.li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = li.inflate(R.layout.n_home_listrowview,parent,false);
        TextView usaha, alamat, jarak, status;
        ImageView avatar;
        avatar = (ImageView)v.findViewById(R.id.img_cover);
        usaha = (TextView)v.findViewById(R.id.judul);
        alamat = (TextView)v.findViewById(R.id.alamat);
        jarak = (TextView)v.findViewById(R.id.jarak);
        status = (TextView)v.findViewById(R.id.status);
        usaha.setText(data.get(position).getUsaha());
        alamat.setText(data.get(position).getAlamat());
        jarak.setText(data.get(position).getJarak());
        status.setText(data.get(position).getStatus());
        if(data.get(position).getStatus().equalsIgnoreCase("buka")){
            status.setTextColor(c.getResources().getColor(R.color.buka));
        }
        else if(data.get(position).getStatus().equalsIgnoreCase("tutup")){
            status.setTextColor(c.getResources().getColor(R.color.tutup));
        }
        Bitmap bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.dummy_avatar);
        avatar.setImageDrawable(new Rounder(bm));
        return v;
    }
}