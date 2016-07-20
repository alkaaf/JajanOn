package com.dalbo.jajanon.Entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alkaaf on 7/10/2016.
 */
public class DataUlasan {
    int id_lapak, id_user;
    String nama, ulasan;
    long tstamp;
    float rating;

    public DataUlasan(int id_lapak, int id_user, String nama, String ulasan, long tstamp, float rating) {
        this.id_lapak = id_lapak;
        this.id_user = id_user;
        this.nama = nama;
        this.ulasan = ulasan;
        this.tstamp = tstamp;
        this.rating = rating;
    }

    public String getUlasan() {
        return ulasan;
    }

    public String getTanggal() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(tstamp*1000));
    }

    public String getNama() {
        return nama;
    }

    public float getRating() {
        return rating;
    }

//    public static ArrayList<DataUlasan> getDummy(){
//        ArrayList<DataUlasan> temp = new ArrayList<>();
//        temp.add(new DataUlasan("Wenaak gan, lanjutkan","12/12/2019","Eden",3));
//        temp.add(new DataUlasan("Menjijikkan","12/12/2019","Dein",2));
//        temp.add(new DataUlasan("Wanchooooor, lanjutkan","14/12/2019","Nein",5));
//        temp.add(new DataUlasan("Memuakkan, basi lanjutkan","13/12/2019","Noir",4));
//        temp.add(new DataUlasan("Gak tau harus komen kayak gimana, blha blea beah beabadsufhkjasbdf jfaksjdffajshdkbauvgiasbdfkjbkvbsdbkadv","11/12/2019","Nah",1));
//        return temp;
//    }
}
