package com.dalbo.jajanon.Service;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Entity.DataLapak;
import com.dalbo.jajanon.Entity.DataUlasan;
import com.dalbo.jajanon.R;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alkaaf on 7/16/2016.
 */
public class SvcLapak {
    URL url;
    Context c;
    Activity act;
    int id;
    int uid;
    JSONObject data;
    String mainUrl;
    Bitmap bmCover;
    DataLapak lapak;

    public SvcLapak(int id, String mainUrl, Context c, Activity act) {
        Pref.init(c);
        try {
            this.url = new URL(mainUrl + "getdetaillapak.php?id=" + id + "&uid=" + Pref.getUid());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        uid = Pref.getUid();
        this.id = id;
        this.c = c;
        this.act = act;
        this.mainUrl = mainUrl;
    }

    public void connect() {
        BufferedReader br = null;
        StringBuilder buff = new StringBuilder();
        String temp;
        try {
            // get data lapak
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((temp = br.readLine()) != null) {
                buff.append(temp);
            }
            data = new JSONObject(buff.toString());
            lapak = new DataLapak(
                    data.getInt("id"),
                    data.getInt("id_user"),
                    data.getString("nama"),
                    data.getString("alamat"),
                    data.getString("sampul"),
                    data.getInt("buka"),
                    data.getInt("tutup"),
                    (float) data.getDouble("rating"),
                    new LatLng(data.getDouble("lat"), data.getDouble("lng")),
                    data.getLong("tstamp")
            );
            // download gambar sampul
            lapak.downloadSampul(mainUrl + "img/cover/");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public DataLapak getDataLapak() {
        return lapak;
    }

    public ArrayList<DataUlasan> getUlasan() {
        ArrayList<DataUlasan> ulas = new ArrayList<>();
        try {
            JSONArray raw = data.getJSONArray("ulasan");
            for (int i = 0; i < raw.length(); i++) {
                JSONObject jo = raw.getJSONObject(i);
                ulas.add(new DataUlasan(jo.getInt("id_lapak"), jo.getInt("id_user"), jo.getString("user"), jo.getString("komentar"), jo.getLong("tstamp"), (float) jo.getDouble("rate")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ulas;
    }

    public int getTotalPelanggan() {
        try {
            return data.getInt("jml_pelanggan");
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isLanggan() {
        try {
            return data.getBoolean("islanggan");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int setLanggan() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(c.getString(R.string.svc) + "setlanggan.php?id=" + id + "&uid=" + uid).openStream()));
            int res = Integer.parseInt(br.readLine());
            return res;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }
}