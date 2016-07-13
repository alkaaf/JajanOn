package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dalbo.jajanon.Core.Autentikasi;
import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/11/2016.
 */
public class login extends Dialog implements View.OnClickListener {
    Button submit;
    EditText username, password;
    Context c;
    Activity act;
    Dialog me;

    public login(Context context, Activity a) {
        super(context);
        c = context;
        act = a;
        me = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_login);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Pref.init(getContext());
        final ProgressDialog pd = new ProgressDialog(c);
        pd.setTitle("Mencoba masuk");
        if (v == submit) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Autentikasi a = new Autentikasi(c.getString(R.string.svc));
                    int res = a.login(username.getText().toString(), password.getText().toString());
                    if(res > 0){
                        Pref.putUid(res);
                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(c,"Login berhasil!",Toast.LENGTH_SHORT).show();
                                me.dismiss();
                            }
                        });
                    } else if (res == 0){
                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(c,"Login gagal, cek kembali email dan password anda",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if( res == -1){
                        act.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(c,"Kesalahan saat login, cak koneksi",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }
}