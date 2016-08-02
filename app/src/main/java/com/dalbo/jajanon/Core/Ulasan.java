package com.dalbo.jajanon.Core;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alkaaf on 7/31/2016.
 */
public class Ulasan {
    String mainUrl;
    URL url;

    public Ulasan(String mainUrl) {
        this.mainUrl = mainUrl;
        try {
            this.url = new URL(mainUrl + "addulasan.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int submit(int uid, int lid, float rating, String ulasan) {
        JSONObject maindata = new JSONObject();
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        try {
            // put data in json format
            maindata.put("uid",uid);
            maindata.put("lid",lid);
            maindata.put("rating",rating);
            maindata.put("ulasan",ulasan);
            // connect to web service
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.connect();
            // get connection outputstream
            dos = new DataOutputStream(conn.getOutputStream());
            // write json data to webservice
            dos.writeBytes("data="+maindata.toString());
            // get response as result
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder buff = new StringBuilder();
            String temp;
            while((temp = br.readLine())!= null){
                buff.append(temp);
            }
            return new JSONObject(buff.toString()).getInt("res");
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (JSONException e) {
            e.printStackTrace();
            return -2;
        }

    }
}
