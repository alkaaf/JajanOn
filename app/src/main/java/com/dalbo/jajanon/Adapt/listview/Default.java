package com.dalbo.jajanon.Adapt.listview;

import android.app.Activity;
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
    Activity act;
    LayoutInflater li;
    // Deklarasi elemen custom listview
    TextView usaha, alamat, status, ratingVal, jarak; //jarak
    ImageView cover;
    RatingBar ratingBar;
    // Deklarasi konstruktor
    public Default(Context context, Activity act, ArrayList<DataLapak> objects) {
        super(context, R.layout.n_home_listrowview, objects);
        this.data = objects;
        this.c = context;
        this.act = act;
        // Deklarasi layout inflater untuk menampilkan XML
        this.li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout n_home_listrowview untuk digunakan sebagai tampilan custom listview
        View v = li.inflate(R.layout.n_home_listrowview,parent,false);

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
        jarak.setText(String.format("%.2f KM",data.get(position).getJarak()));
        int buka = data.get(position).getBuka();
        int tutup = data.get(position).getTutup();
        int now = Integer.parseInt(new SimpleDateFormat("HH").format(new Date())) * 60 + Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        if (buka > tutup) {
            tutup = tutup + 60 * 24;
            now = now + 60 * 24;
        }
        if(now > buka && now < tutup){
            status.setText("BUKA");
            status.setBackgroundResource(R.drawable.bunder_green);
        } else {
            status.setText("TUTUP");
            status.setBackgroundResource(R.drawable.bunder_red);
        }
        if(data.get(position).getBitmapSampul() == null) {
            final int pos = position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.get(pos).downloadSampul(getContext().getString(R.string.svc) + "img/cover/",100,100);
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cover.setImageBitmap(data.get(pos).getBitmapSampul());
                        }
                    });
                }
            }).start();
        } else {
            cover.setImageBitmap(data.get(position).getBitmapSampul());
        }
        return v;
    }
}