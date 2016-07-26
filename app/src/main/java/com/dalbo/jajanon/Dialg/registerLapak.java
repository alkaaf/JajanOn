package com.dalbo.jajanon.Dialg;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dalbo.jajanon.Core.Pref;
import com.dalbo.jajanon.Core.RegisterLapak;
import com.dalbo.jajanon.CustomClass.FileChooser;
import com.dalbo.jajanon.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class registerLapak extends Dialog implements LocationListener, View.OnClickListener, FileChooser.FileSelectedListener {
    TimePickerDialog pickerbuka, pickertutup;
    TextView namaLapak, alamat, fotoLapak;
    EditText jamBuka, jamTutup, cover;
    Button chooseFile, batal, simpan;
    ImageView gpsStatus, preview;
    RelativeLayout notif;
    int minbuka, mintutup;
    int uid;
    String fotoPath;
    double lat, lng;
    boolean gpslocked;
    FileChooser fc;
    File fCover;
    Context c;
    Activity act;
    LocationManager lm;
    Dialog me;

    public registerLapak(Context context, Activity a) {
        super(context);
        c = context;
        act = a;
        lat = 0;
        lng = 0;
        gpslocked = false;
        Pref.init(c);
        uid = Pref.getUid();
        me = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_addlapak);
        fc = new FileChooser(act);
        notif = (RelativeLayout) findViewById(R.id.notif);
        namaLapak = (TextView) findViewById(R.id.namaLapak);
        alamat = (TextView) findViewById(R.id.alamat);
        jamBuka = (EditText) findViewById(R.id.jamBuka);
        jamTutup = (EditText) findViewById(R.id.jamTutup);
        fotoLapak = (TextView) findViewById(R.id.cover);
        chooseFile = (Button) findViewById(R.id.chooseFile);
        batal = (Button) findViewById(R.id.batal);
        simpan = (Button) findViewById(R.id.simpan);
        gpsStatus = (ImageView) findViewById(R.id.gpsStatus);
        preview = (ImageView) findViewById(R.id.preview);
        cover = (EditText)findViewById(R.id.cover);
        fc.setFileListener(this);
        jamBuka.setOnClickListener(this);
        jamTutup.setOnClickListener(this);
        chooseFile.setOnClickListener(this);
        batal.setOnClickListener(this);
        simpan.setOnClickListener(this);
        gpsStatus.setOnClickListener(this);
        lm = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    public void setTime(EditText v, int h, int m) {
        v.setText(timeReformat(h, m));
    }

    @Override
    public void onClick(View v) {
        if (v == jamBuka) {
            pickerbuka = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    minbuka = hourOfDay * 60 + minute;
                    setTime(jamBuka, hourOfDay, minute);
                }
            }, 24, 60, true);
            pickerbuka.show();
        } else if (v == jamTutup) {
            pickertutup = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mintutup = hourOfDay * 60 + minute;
                    setTime(jamTutup, hourOfDay, minute);
                }
            }, 24, 60, true);
            pickertutup.show();
        } else if (v == chooseFile) {
            fc.showDialog();
        } else if (v == batal) {
            this.dismiss();
        } else if (v == gpsStatus) {
            if (gpslocked) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Geocoder gc = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addr = null;
                        try {
                            addr = gc.getFromLocation(lat, lng, 1);
                            final StringBuilder temp = new StringBuilder();
                            temp.append(addr.get(0).getAddressLine(0));
                            temp.append(", ");
                            temp.append(addr.get(0).getSubLocality());
                            temp.append(", ");
                            temp.append(addr.get(0).getLocality());
                            temp.append(", ");
                            temp.append(addr.get(0).getAdminArea());
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    alamat.setText(temp.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } else if (v == simpan) {
            final ProgressDialog pd = new ProgressDialog(c);
            final String sNamaLapak = namaLapak.getText().toString();
            final String sAlamat = alamat.getText().toString();
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
                    RegisterLapak reg = new RegisterLapak(c.getString(R.string.svc));
                    final int res = reg.register(uid, sNamaLapak, sAlamat, lat, lng, minbuka, mintutup, fCover);
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (res == 0) {
                                Toast.makeText(c, "Gagal mendaftarkan, coba lagi", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            } else if (res == 1) {
                                Toast.makeText(c, "Pendaftaran lapak berhasil", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                me.dismiss();
                            } else if (res == -1) {
                                Toast.makeText(c, "Kesalahan aplikasi, coba lagi", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void fileSelected(File file) {
        fCover = file;
        cover.setText(file.getAbsolutePath());
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            Bitmap bm = BitmapFactory.decodeStream(is);
            preview.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        if (lat != 0 && lng != 0) {
            notif.setVisibility(View.GONE);
            gpsStatus.setImageResource(R.mipmap.ic_gpsget);
            gpslocked = true;
        } else {
            gpslocked = false;
        }
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private String timeReformat(int h, int m) {
        String fh, fm;
        if (h < 10) {
            fh = String.format("0%d", h);
        } else {
            fh = String.format("%d", h);
        }

        if (m < 10) {
            fm = String.format("0%d", m);
        } else {
            fm = String.format("%d", m);
        }
        return fh + ":" + fm;
    }
}