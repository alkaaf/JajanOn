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
    public static Bitmap downloadCover(Context c, String id) throws IOException {
        Bitmap bm = null;
        InputStream is = new URL(c.getString(R.string.svc)+"cover/").openStream();
        bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}