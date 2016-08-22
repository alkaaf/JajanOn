package com.dalbo.jajanon.CustomClass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dalbo.jajanon.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
/**
 * Created by alkaaf on 7/20/2016.
 */
public class ImageDL {
    public static Bitmap download(String url) throws IOException {
        Bitmap bm = null;
        InputStream is = new URL(url).openStream();
        bm = BitmapFactory.decodeStream(is);
        return bm;
    }
    public static Bitmap download(String url, int w, int h) throws IOException {
        Bitmap bmMeta, bmRes;
        BitmapFactory.Options getboundopt = new BitmapFactory.Options();
        BitmapFactory.Options samplopt = new BitmapFactory.Options();
        InputStream is = new URL(url).openStream();
        int srcw, srch;
        getboundopt.inJustDecodeBounds = true;
        bmMeta = BitmapFactory.decodeStream(is,null,getboundopt);
        boolean sampleByHeight = getboundopt.outHeight -h > getboundopt.outWidth - w;
        double sampleSize = sampleByHeight ? getboundopt.outHeight/h : getboundopt.outWidth/w;
        samplopt.inSampleSize = (int)Math.pow(2d,Math.floor(Math.log(sampleSize)/Math.log(2d)));
        is.close();
        is = new URL(url).openStream();
        bmRes = BitmapFactory.decodeStream(is,null,samplopt);
        return bmRes;
    }
    public static Bitmap downloadCover(Context c, String id) throws IOException {
        Bitmap bm = null;
        InputStream is = new URL(c.getString(R.string.svc)+"cover/").openStream();
        bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}