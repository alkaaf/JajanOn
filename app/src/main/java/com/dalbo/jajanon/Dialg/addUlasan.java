package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Core.Ulasan;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/21/2016.
 */
public class addUlasan extends Dialog implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    Context c;
    Activity act;
    int uid;
    int lid;
    Dialog me;
    EditText ulasan;
    RatingBar rb;
    Button simpan, batal;

    public addUlasan(int lid, Context context, Activity a) {
        super(context);
        Pref.init(context);
        this.uid = Pref.getUid();
        this.lid = lid;
        this.me = this;
        this.c = context;
        this.act = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_addulasan);
        ulasan = (EditText) findViewById(R.id.ulasan);
        rb = (RatingBar) findViewById(R.id.rating);
        simpan = (Button) findViewById(R.id.simpan);
        batal = (Button) findViewById(R.id.batal);
        simpan.setOnClickListener(this);
        batal.setOnClickListener(this);
        rb.setRating(2.5f);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (ratingBar == rb) {
            rb.setRating(rating);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == simpan) {
            final ProgressDialog pd = new ProgressDialog(c);
            final String sUlasan = ulasan.getText().toString();
            final float frating = rb.getRating();
            pd.setMessage("Tunggu sebentar...");
            pd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Ulasan ul = new Ulasan(c.getString(R.string.svc));
                    final int res = ul.submit(uid, lid, frating, sUlasan);
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (res == 1) {
                                Toast.makeText(c, "Ulasan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                me.dismiss();
                            } else if (res == 0) {
                                Toast.makeText(c, "Penambahan ulasan gagal. Anda telah menambah ulasan sebelumya", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else if (res == -1) {
                                Toast.makeText(c, "Kesalahan koneksi", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else if (res == -2) {
                                Toast.makeText(c, "Kesalahan pada data yang dikirim", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    });
                }
            }).start();
        } else if (v == batal) {
            me.dismiss();
        }
    }
}