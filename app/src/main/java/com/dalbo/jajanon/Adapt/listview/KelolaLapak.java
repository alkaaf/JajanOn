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

import java.util.ArrayList;

/**
 * Created by alkaaf on 7/14/2016.
 */
public class KelolaLapak extends ArrayAdapter<DataLapak> {
    Context c;
    Activity act;
    ArrayList<DataLapak> data;

    public KelolaLapak(Context context, Activity a, ArrayList<DataLapak> objects) {
        super(context, R.layout.n_profile_kelola_listview, objects);
        this.c = context;
        this.data = objects;
        this.act = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.n_profile_kelola_listview, parent, false);
        TextView usaha, alamat, ratingVal;
        RatingBar ratingBar;
        final ImageView cover = (ImageView) v.findViewById(R.id.img_cover);
        ImageView bEdit;
        usaha = (TextView) v.findViewById(R.id.judul);
        alamat = (TextView) v.findViewById(R.id.alamat);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        usaha.setText(data.get(position).getNama());
        alamat.setText(data.get(position).getAlamat());
        ratingBar.setRating(data.get(position).getRating());
        if (data.get(position).getBitmapSampul() == null) {
            final int pos = position;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.get(pos).downloadSampul(getContext().getString(R.string.svc) + "img/cover/", 100, 100);
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