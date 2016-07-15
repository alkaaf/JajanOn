package com.dalbo.jajanon.Entity;

import java.util.ArrayList;

/**
 * Created by alkaaf on 7/10/2016.
 */
public class DataUlasan {
    String ulasan, tanggal, nama;
    float rating;

    public DataUlasan(String ulasan, String tanggal, String nama, float rating) {
        this.ulasan = ulasan;
        this.tanggal = tanggal;
        this.nama = nama;
        this.rating = rating;
    }

    public String getUlasan() {
        return ulasan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getNama() {
        return nama;
    }

    public float getRating() {
        return rating;
    }

    public static ArrayList<DataUlasan> getDummy(){
        ArrayList<DataUlasan> temp = new ArrayList<>();
        temp.add(new DataUlasan("Wenaak gan, lanjutkan","12/12/2019","Eden",3));
        temp.add(new DataUlasan("Menjijikkan","12/12/2019","Dein",2));
        temp.add(new DataUlasan("Wanchooooor, lanjutkan","14/12/2019","Nein",5));
        temp.add(new DataUlasan("Memuakkan, basi lanjutkan","13/12/2019","Noir",4));
        temp.add(new DataUlasan("Gak tau harus komen kayak gimana, blha blea beah beabadsufhkjasbdf jfaksjdffajshdkbauvgiasbdfkjbkvbsdbkadv","11/12/2019","Nah",1));
        return temp;
    }
}
