package com.example.kopmav1.setoran;

public class SetoranItem {
    private String id_tabungan;
    private String id_anggota;
    private String setoran;
    private String nama;

    public SetoranItem(String id_tabungan, String id_anggota, String setoran, String nama){
        this.id_tabungan = id_tabungan;
        this.id_anggota = id_anggota;
        this.setoran = setoran;
        this.nama = nama;
    }

    public String getId_tabungan() {
        return id_tabungan;
    }

    public void setId_tabungan(String id_tabungan) {
        this.id_tabungan = id_tabungan;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getSetoran() {
        return setoran;
    }

    public void setSetoran(String setoran) {
        this.setoran = setoran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
