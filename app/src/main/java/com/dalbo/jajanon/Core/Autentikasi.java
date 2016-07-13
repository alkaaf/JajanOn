package com.dalbo.jajanon.Core;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alkaaf on 7/11/2016.
 */
public class Autentikasi {
    String mainUrl;
    URL url;

    public Autentikasi(String mainUrl) {
        try {
            this.url = new URL(mainUrl + "andropost/login.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int login(String username, String password) {
        HttpURLConnection conn = null;
        BufferedReader br = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuilder buff;
        String temp = null;
        int status = 0;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            os = conn.getOutputStream();
            String data = "username=" + username + "&password=" + password;
            os.write(data.getBytes());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                buff = new StringBuilder();
                while ((temp = br.readLine()) != null) {
                    buff.append(temp);
                }
                JSONObject jo = new JSONObject(buff.toString());
                status = jo.getInt("uid");
                return status;
            } else return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}