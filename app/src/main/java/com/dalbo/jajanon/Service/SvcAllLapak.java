package com.dalbo.jajanon.Service;

import android.app.Activity;
import android.content.Context;

import com.dalbo.jajanon.Entity.DataLapak;
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
 * Created by alkaaf on 8/17/2016.
 */
public class SvcAllLapak {
    String mainUrl;
    Activity act;
    Context c;
    URL url;
    double lat, lng;
    JSONArray datas;
    DataLapak lapak;
    ArrayList<DataLapak> listlapak;
    public SvcAllLapak(String mainUrl, Activity act, Context c) {
        this.mainUrl = mainUrl;
        try {
            url = new URL(mainUrl+"bynew.php?lat="+lat+"&lng="+lng);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.act = act;
        this.c = c;
        listlapak = new ArrayList<>();
    }
    public SvcAllLapak(String mainUrl,String s, Activity act, Context c) {
        this.mainUrl = mainUrl;
        try {
            url = new URL(mainUrl+"bynew.php?lat="+lat+"&lng="+lng+"&s="+s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.act = act;
        this.c = c;
        listlapak = new ArrayList<>();
    }
    public void connect(){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String temp;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            while((temp = br.readLine()) != null){
                sb.append(temp);
            }
            // ono sing keliru
            datas = new JSONArray(sb.toString());
            for(int i = 0;i < datas.length();i++) {
                JSONObject data = datas.getJSONObject(i);
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
                listlapak.add(lapak);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataLapak> getListLapak() {
        return listlapak;
    }
}