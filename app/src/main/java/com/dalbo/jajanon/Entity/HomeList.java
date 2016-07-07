package com.dalbo.jajanon.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by alkaaf on 7/7/2016.
 */
public class HomeList {
    String usaha, alamat, jarak, status;
    int id;

    public HomeList(int id, String usaha, String alamat, String jarak, String status) {
        this.usaha = usaha;
        this.alamat = alamat;
        this.jarak = jarak;
        this.status = status;
        this.id = id;
    }

    public String getUsaha() {
        return usaha;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJarak() {
        return jarak;
    }

    public String getStatus() {
        return status.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public static ArrayList<HomeList> getDummy(){
        ArrayList<HomeList> data = new ArrayList<>();
        data.add(new HomeList(1,"Nasgor pesawat 123","Patung pesawat","0.9 KM","buka"));
        data.add(new HomeList(2,"Sempol Merana","Patung pesawat","0.9 KM","buka"));
        data.add(new HomeList(3,"Nasi Goreng AE","Semanggi Barat","0.98 KM","tutup"));
        data.add(new HomeList(4,"Sossis Tongkol","Sudimoro","0.9 KM","tutup"));
        data.add(new HomeList(5,"Lorem","Patung pesawat","0.9 KM","buka"));
        data.add(new HomeList(6,"Ipsum","Patung pesawat","0.9 KM","tutup"));
        return data;
    }
}
