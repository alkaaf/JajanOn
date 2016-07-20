package com.dalbo.jajanon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dalbo.jajanon.Adapt.pager.LapakPager;
import com.dalbo.jajanon.Service.LapakData;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class LapakActivity extends AppCompatActivity implements View.OnClickListener{
    ProgressDialog loading;
    ViewPager vp;
    LapakData data;
    ImageView cover;
    Context c;
    Activity act;
    Button langganan;
    //tampilkan layout lapak dan memasang adapter pada view pager
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_lapak);
        data = new LapakData(getIntent().getExtras().getInt("lid",-1),getString(R.string.svc),this,this);
        vp =(ViewPager)findViewById(R.id.lapak_content);
        cover = (ImageView)findViewById(R.id.cover);
        langganan = (Button)findViewById(R.id.langganan);
        langganan.setOnClickListener(this);
        c = this;
        act = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading = new ProgressDialog(c);
                        loading.setMessage("Tunggu sebentar...");
                        loading.show();
                    }
                });
                data.connect();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(data.isLanggan()){
                            langganan.setBackgroundResource(R.drawable.bunder_green);
                            langganan.setText("Langganan");
                        } else {
                            langganan.setBackgroundResource(R.drawable.bunder_red);
                            langganan.setText("Tidak berlangganan");
                        }
                        vp.setAdapter(new LapakPager(getSupportFragmentManager(), data));
                        cover.setImageBitmap(data.getDataLapak().getBitmapSampul());
                        loading.dismiss();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if(v == langganan){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int res = data.setLanggan();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if(res > 0){
//                                Toast.makeText(c,"Berlangganan sukses",Toast.LENGTH_SHORT).show();
//                                langganan.setBackgroundResource(R.drawable.bunder_green);
//                                langganan.setText("Langganan");
//                            } else {
//                                Toast.makeText(c,"Berlangganan gagal. Coba lagi!",Toast.LENGTH_SHORT).show();
//                                langganan.setBackgroundResource(R.drawable.bunder_red);
//                                langganan.setText("Tidak berlangganan");
//                            }
//                        }
//                    });
                }
            }).start();

        }
    }
}
