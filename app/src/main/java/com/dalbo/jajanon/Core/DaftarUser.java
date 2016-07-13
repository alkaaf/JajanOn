package com.dalbo.jajanon.Core;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class DaftarUser {
    String addr;
    String mainUrl;
    URL url;

    public DaftarUser(String mainUrl) {
        this.mainUrl = mainUrl;
        this.addr = "andropost/register.php";
        try {
            url = new URL(mainUrl + this.addr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

//    public String daftar(String username, String email, String password, String telp) {
//        BufferedReader br = null;
//        InputStream is = null;
//        OutputStream os = null;
//        HttpURLConnection conn;
//        try {
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            conn.setDoOutput(true);
//            conn.connect();
//            os = conn.getOutputStream();
//            String data = "username=" + username + "&password=" + Hash.sha256(password) + "&email=" + email + "&telp" + telp;
//            os.write(data.getBytes());
//            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                is = conn.getInputStream();
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
