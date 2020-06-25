package com.example.kopmav1;

public class ModelData {
    String id_anggota, setoran;

    public ModelData(){}

    public ModelData(String id_anggota, String setoran){
        this.id_anggota = id_anggota;
        this.setoran = setoran;
    }

    public String  getId_anggota() {return id_anggota;}
    public void setId_anggota (String id_anggota) {this.id_anggota = id_anggota;}
    public String getSetoran() {return setoran;}
    public void setSetoran() {this.setoran=setoran;}
}
