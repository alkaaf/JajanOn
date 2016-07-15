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
 * Created by alkaaf on 7/13/2016.
 */
public class Register {
    String addr;
    String mainUrl;
    URL url;

    public Register(String mainUrl) {
        try {
            this.url = new URL(mainUrl + "register.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int register(String username, String email, String password, String telp) {
        BufferedReader br = null;
        InputStream is = null;
        OutputStream os = null;
        int res = 0;
        StringBuilder buff = null;
        String temp = null;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoOutput(true);
            conn.connect();
            os = conn.getOutputStream();
            String data = "username=" + username + "&password=" + Hash.saltHash(password) + "&email=" + email + "&telp=" + telp;
            os.write(data.getBytes());
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                buff = new StringBuilder();
                while((temp = br.readLine()) != null){
                    buff.append(temp);
                }
                JSONObject jo = new JSONObject(buff.toString());
                res = jo.getInt("res");
                return res;
            } else return -2;
        } catch (IOException e) {
            e.printStackTrace();
            return -2;
        } catch (JSONException e) {
            e.printStackTrace();
            return -2;
        }
    }
}
