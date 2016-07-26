package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Core.Register;
import com.dalbo.jajanon.R;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class register extends Dialog implements View.OnClickListener, TextWatcher {

    Context c;
    Activity act;
    EditText email, password, username, telp, repassword;
    Button reg;
    Dialog me;

    public register(Context context, Activity act) {
        super(context);
        this.c = context;
        this.act = act;
        me = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_daftar);
        username = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        telp = (EditText) findViewById(R.id.telpon);
        reg = (Button) findViewById(R.id.submit);
        reg.setOnClickListener(this);
        repassword.addTextChangedListener(this);
        Pref.init(c);
    }

    @Override
    public void onClick(View v) {
        if (v == reg) {
            if (username.getText().toString().equals("") ||
                    email.getText().toString().equals("") ||
                    password.getText().toString().equals("") ||
                    repassword.getText().toString().equals("") ||
                    telp.getText().toString().equals("")
                    ) {
                Toast.makeText(c, "Cek masukan yang belum diisi", Toast.LENGTH_SHORT).show();
            } else {
                final ProgressDialog pd = new ProgressDialog(c);
                if (repassword.getText().toString().equals(password.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.setMessage("Mendaftarkan...");
                                    pd.show();
                                }
                            });
                            Register du = new Register(c.getString(R.string.svc));
                            final int res = du.register(username.getText().toString(), email.getText().toString(), password.getText().toString(), telp.getText().toString());
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (res == 0) {
                                        Toast.makeText(c, "Registrasi gagal, ulangi lagi", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    } else if (res == -1) {
                                        Toast.makeText(c, "Email telah dipakai, masukkan email lain atau coba login", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    } else if (res == -2) {
                                        Toast.makeText(c, "Registrasi gagal, cek koneksi dan ulangi lagi", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                    } else if (res == 1) {
                                        Toast.makeText(c, "Registrasi sukses,silahkan login untuk melanjutkan", Toast.LENGTH_SHORT).show();
                                        pd.dismiss();
                                        me.dismiss();
                                    }
                                }
                            });
                        }
                    }).start();
                } else {
                    Toast.makeText(c, "Password tidak sama", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (password.getText().toString().equals(repassword.getText().toString())) {
            repassword.setTextColor(c.getResources().getColor(R.color.black));
        } else {
            repassword.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
