package com.dalbo.jajanon.Entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alkaaf on 7/14/2016.
 */
public class DataLapak {
    int id;
    String nama, alamat, sampul;
    int buka, tutup;
    float rating;
    LatLng ll;
    long timestamp;
    Bitmap cover;

    public DataLapak(int id, String nama, String alamat, String sampul, int buka, int tutup, float rating, LatLng ll, long timestamp) {
        this.id = id;
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
            cover = BitmapFactory.decodeStream(new URL(url + getSampul()).openStream());
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

    public static ArrayList<DataLapak> getDummy() {
        ArrayList<DataLapak> data = new ArrayList<>();
        data.add(new DataLapak(1, "Nasgor 123", "Pesawat Suhat", "gambar.png", 6, 20, 2.8f, new LatLng(90, 25), 123456789));
        data.add(new DataLapak(2, "Nasgor 345", "Pesawat Suhat", "gambar.png", 16, 20, 2.8f, new LatLng(90, 25), 123456789));
        data.add(new DataLapak(3, "Nasgor 765", "Pesawat Suhat", "gambar.png", 10, 20, 2.8f, new LatLng(90, 25), 123456789));
        data.add(new DataLapak(4, "Nasgor 987", "Pesawat Suhat", "gambar.png", 16, 20, 2.8f, new LatLng(90, 25), 123456789));
        data.add(new DataLapak(5, "Nasgor 412", "Pesawat Suhat", "gambar.png", 9, 20, 2.8f, new LatLng(90, 25), 123456789));
        return data;
    }
}