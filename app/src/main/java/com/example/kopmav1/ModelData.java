package com.example.kopmav1;

public class ModelData {
    private String id_anggota, setoran;

    public ModelData(String id_anggota, String setoran){
        id_anggota = id_anggota;
        setoran = setoran;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }
    public String  getId_anggota() {return id_anggota;}
    public void setSetoran(String setoran) {
        this.setoran = setoran;
    }
    public String getSetoran() {return setoran;}
}
