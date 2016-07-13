package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class register extends Dialog implements View.OnClickListener{

    Context c;
    Activity act;
    EditText email, password, username, telp;
    Button reg;
    public register(Context context, Activity act) {
        super(context);
        this.c = context;
        this.act = act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_daftar);
    }

    @Override
    public void onClick(View v) {

    }
}
