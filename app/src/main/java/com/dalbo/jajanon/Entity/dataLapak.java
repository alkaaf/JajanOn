package com.dalbo.jajanon.Entity;

import android.graphics.Bitmap;

import com.dalbo.jajanon.CustomClass.ImageDL;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

/**
 * Created by alkaaf on 7/14/2016.
 */
public class DataLapak {
    int id_user;
    int id;
    String nama, alamat, sampul;
    int buka, tutup;
    float rating;
    LatLng ll;
    long timestamp;
    Bitmap cover;

    public DataLapak(int id, int id_user, String nama, String alamat, String sampul, int buka, int tutup, float rating, LatLng ll, long timestamp) {
        this.id = id;
        this.id_user = id_user;
        this.nama = nama;
        this.alamat = alamat;
        this.sampul = sampul;
        this.buka = buka;
        this.tutup = tutup;
        this.rating = rating;
        this.ll = ll;
        this.timestamp = timestamp;
        this.cover = null;
    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getSampul() {
        return sampul;
    }

    public Bitmap getBitmapSampul() {
        return cover;
    }

    public void downloadSampul(String url){
        try {
            cover = ImageDL.download(url + getSampul());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getBuka() {
        return buka;
    }

    public int getTutup() {
        return tutup;
    }

    public float getRating() {
        return rating;
    }

    public LatLng getLl() {
        return ll;
    }

    public long getTimestamp() {
        return timestamp;
    }
}