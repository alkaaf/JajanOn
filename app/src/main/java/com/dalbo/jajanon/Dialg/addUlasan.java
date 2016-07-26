package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/21/2016.
 */
public class addUlasan extends Dialog implements RatingBar.OnRatingBarChangeListener{
    Context c;
    Activity act;
    int uid;
    int lid;
    Dialog me;
    EditText ulasan;
    RatingBar rb;
    public addUlasan(int uid, int lid, Context context, Activity a) {
        super(context);
        this.uid = uid;
        this.lid = lid;
        this.me = this;
        this.c = context;
        this.act = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ulasan = (EditText)findViewById(R.id.ulasan);
        rb = (RatingBar) findViewById(R.id.ratingBar);
        setContentView(R.layout.popup_addulasan);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if(ratingBar == rb){
            rb.setRating(rating);
        }
    }
}