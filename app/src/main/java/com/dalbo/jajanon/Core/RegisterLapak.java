package com.dalbo.jajanon.Core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class RegisterLapak {
    String addr;
    String mainUrl;
    URL url;

    public RegisterLapak(String mainUrl) {
        try {
            this.url = new URL(mainUrl + "registerlapak.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int register(int uid, String nama, String alamat, double lat, double lng, int buka, int tutup, File cover) {
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        JSONObject maindata = new JSONObject();
        try {
            Bitmap bm = BitmapFactory.decodeStream(new FileInputStream(cover));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,90,baos);
            byte[] imgbyte = baos.toByteArray();
            String imgStr = Base64.encodeToString(imgbyte, Base64.DEFAULT);
            maindata.put("uid", uid);
            maindata.put("nama", nama);
            maindata.put("alamat", alamat);
            maindata.put("lat", lat);
            maindata.put("lng", lng);
            maindata.put("buka", buka);
            maindata.put("tutup", tutup);
            maindata.put("img_base64", imgStr);
//            maindata.put("img_ext",ext);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.connect();
            // send data
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes("data="+maindata.toString());
            // result
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder buff = new StringBuilder();
            String temp;
            while((temp = br.readLine()) != null){
                buff.append(temp);
            }
            return 1;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
