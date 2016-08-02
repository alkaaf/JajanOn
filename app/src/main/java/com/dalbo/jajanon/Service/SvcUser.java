package com.dalbo.jajanon.Service;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dalbo.jajanon.CustomClass.ImageDL;
import com.dalbo.jajanon.Entity.DataLapak;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alkaaf on 7/15/2016.
 */
public class SvcUser {
    URL url;
    Context c;
    Activity act;
    int uid;
    JSONObject data;
    String mainUrl;
    Bitmap bmAvatar;
    ArrayList<DataLapak> lapakLanggan, lapakKu;

    public SvcUser(int id, String mainUrl, Context c, Activity a) {
        try {
            this.url = new URL(mainUrl + "getuserdata.php?uid=" + id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.uid = id;
        this.c = c;
        this.act = a;
        this.mainUrl = mainUrl;
        lapakLanggan = new ArrayList<>();
        lapakKu = new ArrayList<>();
    }

    public void connect() {
        BufferedReader br = null;
        JSONArray ja = null;
        try {
            // dapatkan data usr
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buff = new StringBuilder();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                buff.append(temp);
            }
            data = new JSONObject(buff.toString());
            // dapatkan avatar user
            if (getAvatar() == "null") {
                bmAvatar = BitmapFactory.decodeResource(act.getResources(), R.drawable.dummy_avatar);
            } else {
                bmAvatar = ImageDL.download(mainUrl + "img/avatar/" + getAvatar());
            }
            // dapatkan daftar lapak langganan user

            ja = new JSONArray(readService(mainUrl + "getlapaklanggan.php?uid=" + uid));
            for (int i = 0; i < ja.length(); i++) {
                JSONObject lapak = ja.getJSONObject(i);
                lapakLanggan.add(new DataLapak(
                        lapak.getInt("id"),
                        lapak.getInt("id_user"),
                        lapak.getString("nama"),
                        lapak.getString("alamat"),
                        lapak.getString("sampul"),
                        lapak.getInt("buka"),
                        lapak.getInt("tutup"),
                        (float) lapak.getDouble("rating"),
                        new LatLng(lapak.getDouble("lat"), lapak.getDouble("lng")),
                        lapak.getLong("tstamp")));
                lapakLanggan.get(i).downloadSampul(mainUrl + "img/cover/");
            }
            // dapatkan daftar lapak user

            ja = new JSONArray(readService(mainUrl + "getlapakku.php?uid=" + uid));
            for (int i = 0; i < ja.length(); i++) {
                JSONObject lapak = ja.getJSONObject(i);
                lapakKu.add(new DataLapak(
                        lapak.getInt("id"),
                        lapak.getInt("id_user"),
                        lapak.getString("nama"),
                        lapak.getString("alamat"),
                        lapak.getString("sampul"),
                        lapak.getInt("buka"),
                        lapak.getInt("tutup"),
                        (float) lapak.getDouble("rating"),
                        new LatLng(lapak.getDouble("lat"), lapak.getDouble("lng")),
                        lapak.getLong("tstamp")));
                lapakKu.get(i).downloadSampul(mainUrl + "img/cover/");
            }
            int p = 0;
            p = p + 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String readService(String url) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder buff = new StringBuilder();
        String temp = null;
        while ((temp = br.readLine()) != null) {
            buff.append(temp);
        }
        return buff.toString();
    }

    public String getUsername() {
        try {
            return data.getString("user");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getAvatar() {
        try {
            return data.getString("avatar");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getEmail() {
        try {
            return data.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTelp() {
        try {
            return data.getString("telp");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long getTimestamp() {
        try {
            return data.getLong("tstamp");
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getJumlahLapak() {
        return lapakKu.size();
    }

    public int getJumlahLanggan() {
        return lapakLanggan.size();
    }

    public String getTanggalDaftar() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(getTimestamp() * 1000));
    }

    public Bitmap getBitmapAvatar() {
        return bmAvatar;
    }

    public ArrayList<DataLapak> getLapakLanggan() {
        return lapakLanggan;
    }

    public ArrayList<DataLapak> getLapakKu() {
        return lapakKu;
    }
}