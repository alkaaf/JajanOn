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
import android.widget.TextView;
import android.widget.Toast;

import com.dalbo.jajanon.Adapt.pager.LapakPager;
import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Service.SvcLapak;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class LapakActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressDialog loading;
    ViewPager vp;
    SvcLapak data;
    ImageView cover;
    TextView status;
    Context c;
    Activity act;
    Button langganan;

    //tampilkan layout lapak dan memasang adapter pada view pager
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_lapak);
        Pref.init(this);
        data = new SvcLapak(getIntent().getExtras().getInt("lid", -1), getString(R.string.svc), this, this);
        vp = (ViewPager) findViewById(R.id.lapak_content);
        cover = (ImageView) findViewById(R.id.cover);
        langganan = (Button) findViewById(R.id.langganan);
        status = (TextView) findViewById(R.id.status);
        langganan.setOnClickListener(this);
        c = this;
        act = this;
        if(Pref.getUid() == 0){
            langganan.setVisibility(View.GONE);
        }
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
                        if (data.isLanggan()) {
                            langganan.setBackgroundResource(R.drawable.bunder_green);
                            langganan.setText("Berlangganan");
                        } else {
                            langganan.setBackgroundResource(R.drawable.bunder_red);
                            langganan.setText("Tidak berlangganan");
                        }
                        vp.setAdapter(new LapakPager(getSupportFragmentManager(), data));
                        cover.setImageBitmap(data.getDataLapak().getBitmapSampul());
                        // ngolah status
                        int minbuka, mintutup, now;
                        minbuka = data.getDataLapak().getBuka();
                        mintutup = data.getDataLapak().getTutup();
                        now = Integer.parseInt(new SimpleDateFormat("HH").format(new Date())) * 60 + Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
                        if (minbuka > mintutup) {
                            mintutup = mintutup + 60 * 24;
                            now = now + 60 * 24;
                        }
                        if (now > minbuka && now < mintutup) {
                            // nek buka lapo
                            status.setText("BUKA");
                            status.setBackgroundResource(R.drawable.bunder_green);
                        } else {
                            //nek tutup lapo
                            status.setText("TUTUP");
                            status.setBackgroundResource(R.drawable.bunder_red);
                        }
                        loading.dismiss();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        if (v == langganan) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Tunggu sebentar");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.show();
                        }
                    });
                    final int res = data.setLanggan();
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            if(res == 1){
                                Toast.makeText(c,"Berhasil berlangganan",Toast.LENGTH_SHORT).show();
                                langganan.setBackgroundResource(R.drawable.bunder_green);
                                langganan.setText("BERLANGGANAN");
                            }
                            else if(res == 2){
                                Toast.makeText(c,"Berhasil berhenti berlangganan", Toast.LENGTH_SHORT).show();
                                langganan.setBackgroundResource(R.drawable.bunder_red);
                                langganan.setText("TIDAK BERLANGGANAN");
                            } else{
                                Toast.makeText(c,"Kesalahan, ulangi lagi",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        data.getDataLapak().flushCover();
    }
}
