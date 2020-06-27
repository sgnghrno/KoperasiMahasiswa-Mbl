package com.example.kopmav1.penarikan;

public class PenarikanItem {
    String id_tabungan;
    String id_anggota;
    String nama;
    String penarikan;

    public PenarikanItem(String id_tabungan, String id_anggota, String nama, String penarikan) {
        this.id_tabungan = id_tabungan;
        this.id_anggota = id_anggota;
        this.nama = nama;
        this.penarikan = penarikan;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPenarikan() {
        return penarikan;
    }

    public void setPenarikan(String penarikan) {
        this.penarikan = penarikan;
    }
}
