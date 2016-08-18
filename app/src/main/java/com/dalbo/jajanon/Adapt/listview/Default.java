package com.dalbo.jajanon.Adapt.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dalbo.jajanon.Entity.DataLapak;
import com.dalbo.jajanon.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class Default extends ArrayAdapter<DataLapak> {
    // Deklarasi variabel untuk penyimpanan data
    ArrayList<DataLapak> data;
    Context c;
    LayoutInflater li;

    // Deklarasi konstruktor
    public Default(Context context, ArrayList<DataLapak> objects) {
        super(context, R.layout.n_home_listrowview, objects);
        this.data = objects;
        this.c = context;
        // Deklarasi layout inflater untuk menampilkan XML
        this.li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout n_home_listrowview untuk digunakan sebagai tampilan custom listview
        View v = li.inflate(R.layout.n_home_listrowview,parent,false);
        // Deklarasi elemen custom listview
        TextView usaha, alamat, status, ratingVal, jarak; //jarak
        ImageView cover;
        RatingBar ratingBar;
        ratingBar = (RatingBar)v.findViewById(R.id.ratingBar);
        ratingVal  = (TextView)v.findViewById(R.id.ratingVal);
        cover = (ImageView)v.findViewById(R.id.img_cover);
        usaha = (TextView)v.findViewById(R.id.judul);
        alamat = (TextView)v.findViewById(R.id.alamat);
        jarak = (TextView)v.findViewById(R.id.jarak);
        status = (TextView)v.findViewById(R.id.status);

        // Memasukkan data kedalam view
        ratingVal.setText(data.get(position).getRating()+"");
        ratingBar.setRating(data.get(position).getRating());
        usaha.setText(data.get(position).getNama());
        alamat.setText(data.get(position).getAlamat());
        cover.setImageBitmap(data.get(position).getBitmapSampul());
        int buka = data.get(position).getBuka();
        int tutup = data.get(position).getTutup();
        int now = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        if(buka > tutup){
            buka += 12*60*60;
        }
        if(now > buka && now < tutup){
            status.setTextColor(c.getResources().getColor(R.color.buka));
            status.setText("BUKA");
        } else {
            status.setTextColor(c.getResources().getColor(R.color.tutup));
            status.setText("TUTUP");
        }

        return v;
    }
}