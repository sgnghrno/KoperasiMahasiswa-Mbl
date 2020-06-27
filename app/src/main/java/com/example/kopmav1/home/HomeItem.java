package com.example.kopmav1.home;

public class HomeItem {
    private String id_anggota;
    private String nama_anggota;
    private String total_setoran;
    private String total_penarikan;
    private String total_saldo;

    public HomeItem(String id_anggota, String nama_anggota, String total_setoran, String total_penarikan, String total_saldo) {
        this.id_anggota = id_anggota;
        this.nama_anggota = nama_anggota;
        this.total_setoran = total_setoran;
        this.total_penarikan = total_penarikan;
        this.total_saldo = total_saldo;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }

    public String getTotal_setoran() {
        return total_setoran;
    }

    public void setTotal_setoran(String total_setoran) {
        this.total_setoran = total_setoran;
    }

    public String getTotal_penarikan() {
        return total_penarikan;
    }

    public void setTotal_penarikan(String total_penarikan) {
        this.total_penarikan = total_penarikan;
    }

    public String getTotal_saldo() {
        return total_saldo;
    }

    public void setTotal_saldo(String total_saldo) {
        this.total_saldo = total_saldo;
    }
}
