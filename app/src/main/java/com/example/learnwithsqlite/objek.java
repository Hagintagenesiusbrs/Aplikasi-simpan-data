package com.example.learnwithsqlite;

public class objek {
    String id="",nama="",alamat="";
    objek(String id, String nama, String alamat)
    {
        this.id=id;
        this.nama=nama;
        this.alamat=alamat;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
}
